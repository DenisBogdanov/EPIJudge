#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

// Check if a partially filled matrix has any conflicts.
bool IsValidSudoku(const vector<vector<int>> &partial_assignment) {
    set<string> seen;

    for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; ++c) {
            int num = partial_assignment[r][c];
            if (num == 0) continue;
            string rowStr = "Seen " + to_string(num) + " on row " + to_string(r);
            string colStr = "Seen " + to_string(num) + " on col " + to_string(c);
            string cellStr = "Seen " + to_string(num) + " inside cell " + to_string(r / 3) + "x" + to_string(c / 3);

            if (seen.find(rowStr) != seen.end() || seen.find(colStr) != seen.end() ||
                seen.find(cellStr) != seen.end()) {
                return false;
            }

            seen.insert(rowStr);
            seen.insert(colStr);
            seen.insert(cellStr);
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
