#include <vector>

#include "test_framework/generic_test.h"
using std::vector;

bool MatrixSearch(const vector<vector<int> > &grid, int x) {
    if (grid.empty()) {
        return false;
    }
    int r = grid.size() - 1;
    int c = 0;
    while (r >= 0 && c < grid[0].size()) {
        if (grid[r][c] == x) {
            return true;
        }
        if (grid[r][c] < x) {
            c++;
        } else {
            r--;
        }
    }
    return false;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A", "x"};
    return GenericTestMain(args, "search_row_col_sorted_matrix.cc",
                           "search_row_col_sorted_matrix.tsv", &MatrixSearch,
                           DefaultComparator{}, param_names);
}
