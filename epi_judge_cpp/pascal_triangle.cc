#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<vector<int>> GeneratePascalTriangle(int num_rows) {
    if (num_rows == 0) return {};
    vector<vector<int>> result;
    result.push_back({1});

    for (int row = 1; row < num_rows; row++) {
        vector<int> currRow;
        currRow.push_back(1);
        vector<int> prevRow = result[row - 1];
        for (int i = 1; i < prevRow.size(); i++) {
            currRow.push_back(prevRow[i - 1] + prevRow[i]);
        }

        currRow.push_back(1);
        result.push_back(currRow);
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"num_rows"};
    return GenericTestMain(args, "pascal_triangle.cc", "pascal_triangle.tsv",
                           &GeneratePascalTriangle, DefaultComparator{},
                           param_names);
}
