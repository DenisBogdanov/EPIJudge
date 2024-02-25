#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<int> MatrixInSpiralOrder(const vector<vector<int>> &square_matrix) {
    vector<int> result;
    if (square_matrix.empty() || square_matrix[0].empty()) return result;

    int rows = square_matrix.size();
    int columns = square_matrix[0].size();

    int top = 0;
    int bottom = rows - 1;
    int left = 0;
    int right = columns - 1;

    while (result.size() < rows * columns) {
        for (int c = left; c <= right; ++c) {
            result.push_back(square_matrix[top][c]);
        }
        ++top;

        for (int r = top; r <= bottom; ++r) {
            result.push_back(square_matrix[r][right]);
        }
        --right;

        for (int c = right; c >= left; --c) {
            result.push_back(square_matrix[bottom][c]);
        }
        --bottom;

        for (int r = bottom; r >= top; --r) {
            result.push_back(square_matrix[r][left]);
        }
        ++left;
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"square_matrix"};
    return GenericTestMain(args, "spiral_ordering.cc", "spiral_ordering.tsv",
                           &MatrixInSpiralOrder, DefaultComparator{},
                           param_names);
}
