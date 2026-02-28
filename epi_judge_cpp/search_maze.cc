#include <istream>
#include <string>
#include <vector>

#include "test_framework/generic_test.h"
#include "test_framework/serialization_traits.h"
#include "test_framework/test_failure.h"
#include "test_framework/timed_executor.h"
using namespace std;

const int DIRS[4][2] = {
    {-1, 0},
    {1, 0},
    {0, -1},
    {0, 1}
};

enum class Color { kWhite, kBlack };

struct Coordinate {
    bool operator==(const Coordinate &that) const {
        return x == that.x && y == that.y;
    }

    int x, y;
};

vector<Coordinate> SearchMaze(vector<vector<Color> > maze, const Coordinate &s, const Coordinate &e) {
    queue<Coordinate> q;
    q.push(s);
    vector visited(maze.size(), vector(maze[0].size(), false));
    visited[s.x][s.y] = true;
    vector prev(maze.size(), vector<Coordinate>(maze[0].size()));
    while (!q.empty()) {
        auto [x, y] = q.front();
        q.pop();
        for (auto &[dx, dy] : DIRS) {
            int new_x = x + dx;
            int new_y = y + dy;

            if (e.x == new_x && e.y == new_y) {
                prev[new_x][new_y] = {x, y};
                vector<Coordinate> ans;
                Coordinate curr = e;
                while (!(curr.x == s.x && curr.y == s.y)) {
                    ans.push_back(curr);
                    curr = prev[curr.x][curr.y];
                }
                ans.push_back(s);
                reverse(ans.begin(), ans.end());
                return ans;
            }

            if (new_x < 0 || new_x >= maze.size() || new_y < 0 || new_y >= maze[0].size()) continue;
            if (maze[new_x][new_y] == Color::kBlack || visited[new_x][new_y]) continue;
            visited[new_x][new_y] = true;
            prev[new_x][new_y] = {x, y};
            q.push({new_x, new_y});
        }
    }

    return {};
}

namespace test_framework {
    template<>
    struct SerializationTrait<Color> : SerializationTrait<int> {
        using serialization_type = Color;

        static serialization_type Parse(const json &json_object) {
            return static_cast<serialization_type>(
                SerializationTrait<int>::Parse(json_object));
        }
    };
} // namespace test_framework

namespace test_framework {
    template<>
    struct SerializationTrait<Coordinate> : UserSerTrait<Coordinate, int, int> {
        static std::vector<std::string> GetMetricNames(const std::string &arg_name) {
            return {};
        }

        static std::vector<int> GetMetrics(const Coordinate &x) { return {}; }
    };
} // namespace test_framework

bool PathElementIsFeasible(const vector<vector<Color> > &maze,
                           const Coordinate &prev, const Coordinate &cur) {
    if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y &&
          cur.y < maze[cur.x].size() && maze[cur.x][cur.y] == Color::kWhite)) {
        return false;
    }
    return cur == Coordinate{prev.x + 1, prev.y} ||
           cur == Coordinate{prev.x - 1, prev.y} ||
           cur == Coordinate{prev.x, prev.y + 1} ||
           cur == Coordinate{prev.x, prev.y - 1};
}

bool SearchMazeWrapper(TimedExecutor &executor,
                       const vector<vector<Color> > &maze, const Coordinate &s,
                       const Coordinate &e) {
    vector<vector<Color> > copy = maze;

    auto path = executor.Run([&] { return SearchMaze(copy, s, e); });

    if (path.empty()) {
        return s == e;
    }

    if (!(path.front() == s) || !(path.back() == e)) {
        throw TestFailure("Path doesn't lay between start and end points");
    }

    for (size_t i = 1; i < path.size(); i++) {
        if (!PathElementIsFeasible(maze, path[i - 1], path[i])) {
            throw TestFailure("Path contains invalid segments");
        }
    }

    return true;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "maze", "s", "e"};
    return GenericTestMain(args, "search_maze.cc", "search_maze.tsv",
                           &SearchMazeWrapper, DefaultComparator{}, param_names);
}
