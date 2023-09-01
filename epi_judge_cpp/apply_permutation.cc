#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

void ApplyPermutation(vector<int> perm, vector<int> *A_ptr) {
    vector<int> &arr = *A_ptr;

    unsigned long long int n = perm.size();

    for (int i = 0; i < n; ++i) {
        if (perm[i] < 0) continue;
        int prev = arr[i];

        int next = i;
        while (perm[next] >= 0) {
            int putIndex = perm[next];
            perm[next] -= n;

            int temp = arr[putIndex];
            arr[putIndex] = prev;
            prev = temp;
            next = putIndex;
        }
    }

    for (int &num: perm) {
        num += n;
    }
}

vector<int> ApplyPermutationWrapper(const vector<int> &perm, vector<int> A) {
    ApplyPermutation(perm, &A);
    return A;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"perm", "A"};
    return GenericTestMain(args, "apply_permutation.cc", "apply_permutation.tsv",
                           &ApplyPermutationWrapper, DefaultComparator{},
                           param_names);
}
