#include <vector>

#include "test_framework/generic_test.h"

using std::vector;
using std::swap;

vector<vector<int>> result;

void recur(vector<int>& v, int index);

vector<vector<int>> Permutations(vector<int> v) {
    result.clear();

    recur(v, 0);

    return result;
}

void recur(vector<int>& v, int index) {
    if (index == v.size()) {
        result.emplace_back(v);
        return;
    }

    for (int i = index; i != v.size(); ++i) {
        swap(v[i], v[index]);

        recur(v, index + 1);
        swap(v[i], v[index]);
    }
}

int main(int argc, char* argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A"};
    return GenericTestMain(args, "permutations.cc", "permutations.tsv",
                           &Permutations, UnorderedComparator{}, param_names);
}
