#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<int> NextPermutation(vector<int> perm) {
    // 234
    // 243
    // 324

    int lessIndex = -1;
    for (int i = perm.size() - 1; i > 0; --i) {
        if (perm[i - 1] < perm[i]) {
            lessIndex = i - 1;
            break;
        }
    }

    if (lessIndex == -1) return {};
    int greaterIndex = perm.size() - 1;
    while (perm[greaterIndex] <= perm[lessIndex]) {
        greaterIndex--;
    }

    std::swap(perm[lessIndex], perm[greaterIndex]);

    int left = lessIndex + 1;
    int right = perm.size() - 1;

    while (left < right) {
        std::swap(perm[left], perm[right]);
        ++left;
        --right;
    }

    return perm;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"perm"};
    return GenericTestMain(args, "next_permutation.cc", "next_permutation.tsv",
                           &NextPermutation, DefaultComparator{}, param_names);
}
