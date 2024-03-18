#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<vector<int>> result;

void recur(int n, int index, int k, vector<int>& curr);

vector<vector<int>> Combinations(int n, int k) {
    result.clear();

    if (k == 0) {
        result.emplace_back();
        return result;
    }

    vector<int> curr;
    recur(n, 0, k, curr);

    return result;
}

void recur(int n, int index, int k, vector<int>& curr) {
    if (curr.size() == k) {
        result.emplace_back(curr);
        return;
    }

    if (index == n) {
        return;
    }

    if (n - index < k - curr.size()) {
        return;
    }

    curr.push_back(index + 1);
    recur(n, index + 1, k, curr);

    curr.pop_back();
    recur(n, index + 1, k, curr);
}

int main(int argc, char* argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"n", "k"};
    return GenericTestMain(args, "combinations.cc", "combinations.tsv",
                           &Combinations, UnorderedComparator{}, param_names);
}
