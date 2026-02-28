#include <vector>

#include "test_framework/generic_test.h"
using std::vector;

vector<vector<int> > GeneratePascalTriangle(int num_rows) {
    vector<vector<int>> ans;
    if (num_rows == 0) return ans;
    ans.push_back({1});
    for (int level = 2; level <= num_rows; level++) {
        vector<int> next;
        next.push_back(1);
        const auto &prev = ans[level - 2];
        for (int i = 0; i < prev.size() - 1; i++) {
            next.push_back(prev[i] + prev[i + 1]);

        }
        next.push_back(1);
        ans.push_back(next);
    }
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"num_rows"};
    return GenericTestMain(args, "pascal_triangle.cc", "pascal_triangle.tsv",
                           &GeneratePascalTriangle, DefaultComparator{},
                           param_names);
}
