#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

bool MatrixSearch(const vector<vector<int>> &matrix, int x) {
    int rows = matrix.size();
    if (rows == 0) return false;
    int columns = matrix[0].size();
    if (columns == 0) return false;

    int r = rows - 1;
    int c = 0;

    while (r >= 0 && c < columns) {
        if (matrix[r][c] == x) {
            return true;
        } else if (matrix[r][c] > x) {
            --r;
        } else {
            c++;
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
