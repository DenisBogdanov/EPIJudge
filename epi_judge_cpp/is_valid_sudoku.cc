#include <vector>

#include "test_framework/generic_test.h"
using namespace std;
// Check if a partially filled matrix has any conflicts.
bool IsValidSudoku(const vector<vector<int> > &partial_assignment) {
    unordered_set<string> checker;
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            if (partial_assignment[i][j] == 0) continue;
            string row = "row " + to_string(i) + " contains " + to_string(partial_assignment[i][j]);
            string column = "column " + to_string(j) + " contains " + to_string(partial_assignment[i][j]);
            string cell = "cell " + to_string(i / 3) + ":" + to_string(j / 3) + " contains " + to_string(partial_assignment[i][j]);
            if (checker.count(row) || checker.count(column) || checker.count(cell)) return false;
            checker.insert(row);
            checker.insert(column);
            checker.insert(cell);
        }
    }

    return true;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"partial_assignment"};
    return GenericTestMain(args, "is_valid_sudoku.cc", "is_valid_sudoku.tsv",
                           &IsValidSudoku, DefaultComparator{}, param_names);
}
