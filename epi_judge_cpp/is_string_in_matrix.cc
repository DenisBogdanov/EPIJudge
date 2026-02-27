#include <vector>

#include "test_framework/generic_test.h"
using std::vector;

const int DIRS[4][2] = {
    {-1, 0},
    {1, 0},
    {0, -1},
    {0, 1}
};

bool helper(const vector<vector<int> > &grid, int r, int c, const vector<int> &pattern, int idx) {
    if (grid[r][c] != pattern[idx]) return false;
    if (idx == pattern.size() - 1) return true;
    for (auto &[dr, dc]: DIRS) {
        int next_r = r + dr;
        int next_c = c + dc;

        if (next_r < 0 || next_r == grid.size() || next_c < 0 || next_c == grid[0].size()) continue;
        if (helper(grid, next_r, next_c, pattern, idx + 1)) {
            return true;
        }
    }
    return false;
}

bool IsPatternContainedInGrid(const vector<vector<int> > &grid, const vector<int> &pattern) {
    for (int i = 0; i < grid.size(); i++) {
        for (int j = 0; j < grid[0].size(); j++) {
            if (helper(grid, i, j, pattern, 0)) return true;
        }
    }
    return false;
}


int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"grid", "pattern"};
    return GenericTestMain(args, "is_string_in_matrix.cc",
                           "is_string_in_matrix.tsv", &IsPatternContainedInGrid,
                           DefaultComparator{}, param_names);
}
