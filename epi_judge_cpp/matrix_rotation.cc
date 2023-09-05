#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

void RotateMatrix(vector<vector<int>> *square_matrix_ptr) {
    vector<vector<int>> &matrix = *square_matrix_ptr;
    int n = matrix.size();

    // vertical symmetry shift
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < n / 2; c++) {
            swap(matrix[r][c], matrix[r][n - c - 1]);
        }
    }

    // main diagonal symmetry shift
    for (int r = 0; r < n - 1; r++) {
        for (int c = 0; c < n - r - 1; c++) {
            swap(matrix[r][c], matrix[n - c - 1][n - r - 1]);
        }
    }
}

vector<vector<int>> RotateMatrixWrapper(vector<vector<int>> square_matrix) {
    RotateMatrix(&square_matrix);
    return square_matrix;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"square_matrix"};
    return GenericTestMain(args, "matrix_rotation.cc", "matrix_rotation.tsv",
                           &RotateMatrixWrapper, DefaultComparator{},
                           param_names);
}
