#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<int> NextPermutation(vector<int> perm) {
    int n = perm.size();
    int currMaxIndex = n - 1;
    int lessIndex = -1;

    for (int i = n - 2; i >= 0; i--) {
        if (perm[i] < perm[currMaxIndex]) {
            lessIndex = i;
            break;
        } else if (perm[i] > perm[currMaxIndex]) {
            currMaxIndex = i;
        }
    }

    if (lessIndex == -1) return {};

    int greaterIndex = currMaxIndex;

    for (int i = lessIndex + 1; i < n; i++) {
        if (perm[lessIndex] < perm[i]) {
            if (perm[i] <= perm[greaterIndex]) {
                greaterIndex = i;
            }
        }
    }

    std::swap(perm[lessIndex], perm[greaterIndex]);
    std::reverse(perm.begin() + lessIndex + 1, perm.end());

    return perm;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"perm"};
    return GenericTestMain(args, "next_permutation.cc", "next_permutation.tsv",
                           &NextPermutation, DefaultComparator{}, param_names);
}
