#include <algorithm>
#include <iterator>
#include <vector>

#include "test_framework/generic_test.h"
using namespace std;

bool ok(int col, const vector<int> &curr_arrangement) {
    for (int row = 0; row < curr_arrangement.size(); row++) {
        if (curr_arrangement[row] == col) return false;
        if (abs(col - curr_arrangement[row]) == curr_arrangement.size() - row) return false;
    }
    return true;
}

void recur(int r, int n, vector<int> &curr_arrangement, vector<vector<int>> &ans) {
    if (r == n) {
        const auto successful_arrangement = curr_arrangement;
        ans.push_back(successful_arrangement);
        return;
    }
    for (int col = 0; col < n; col++) {
        if (ok(col, curr_arrangement)) {
            curr_arrangement.push_back(col);
            recur(r + 1, n, curr_arrangement, ans);
            curr_arrangement.pop_back();
        }
    }
}

vector<vector<int> > NQueens(int n) {
    vector<vector<int>> ans;
    vector<int> curr_arrangement;
    recur(0, n, curr_arrangement, ans);
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"n"};
    return GenericTestMain(args, "n_queens.cc", "n_queens.tsv", &NQueens,
                           UnorderedComparator{}, param_names);
}
