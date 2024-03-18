#include <algorithm>
#include <iterator>
#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<vector<int>> result;

void recur(int n, int col, vector<int>& curr);

bool ok(int n, const vector<int>& curr, int row);

vector<vector<int>> NQueens(int n) {
    result.clear();

    vector<int> curr;
    recur(n, 0, curr);

    return result;
}

void recur(int n, int col, vector<int>& curr) {
    if (curr.size() == n) {
        result.emplace_back(curr);
        return;
    }

    for (int row = 0; row < n; ++row) {
        if (ok(n, curr, row)) {
            curr.push_back(row);
            recur(n, col + 1, curr);
            curr.pop_back();
        }
    }
}

bool ok(int n, const vector<int>& curr, int row) {
    if (std::find(curr.begin(), curr.end(), row) != curr.end()) {
        return false;
    }

    int nextCol = static_cast<int>(curr.size());

    for (int col = 0; col != curr.size(); ++col) {
        if ((col + curr[col] == nextCol + row) || (col - curr[col] == nextCol - row)) {
            return false;
        }
    }

    return true;
}

int main(int argc, char* argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"n"};
    return GenericTestMain(args, "n_queens.cc", "n_queens.tsv", &NQueens,
                           UnorderedComparator{}, param_names);
}
