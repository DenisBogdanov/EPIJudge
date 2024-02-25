#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<vector<int>> GeneratePascalTriangle(int num_rows) {
    vector<vector<int>> result;
    if (num_rows == 0) return result;
    result.push_back({1});
    for (int i = 1; i < num_rows; ++i) {
        auto prev = result[i - 1];
        vector<int> curr;
        curr.push_back(1);

        for (int j = 0; j < prev.size() - 1; ++j) {
            curr.push_back(prev[j] + prev[j + 1]);
        }

        curr.push_back(1);
        result.push_back(curr);
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
