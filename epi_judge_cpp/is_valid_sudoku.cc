#include <vector>
#include <set>

#include "test_framework/generic_test.h"

using namespace std;

// Check if a partially filled matrix has any conflicts.
bool IsValidSudoku(const vector<vector<int>> &partial_assignment) {
    set<string> s;

    for (int r = 0; r < 9; ++r) {
        for (int c = 0; c < 9; ++c) {
            if (partial_assignment[r][c] == 0) continue;

            string row = "Seen " + to_string(partial_assignment[r][c]) + " on row " + to_string(r);
            string col = "Seen " + to_string(partial_assignment[r][c]) + " on col " + to_string(c);
            string cell = "Seen " + to_string(partial_assignment[r][c]) + " on cell " + to_string(r / 3) + "x" +
                    to_string(c / 3);

            if (s.find(row) != s.end() || s.find(col) != s.end() || s.find(cell) != s.end()) {
                return false;
            }

            s.insert(row);
            s.insert(col);
            s.insert(cell);
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
