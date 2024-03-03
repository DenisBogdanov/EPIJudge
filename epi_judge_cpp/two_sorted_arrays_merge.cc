#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

void MergeTwoSortedArrays(vector<int> &a, int m, const vector<int> &b, int n) {
    int writeIndex = m + n - 1;
    int i = m - 1;
    int j = n - 1;

    while (i >= 0 || j >= 0) {
        if (i < 0) a[writeIndex] = b[j--];
        else if (j < 0) a[writeIndex] = a[i--];
        else if (a[i] > b[j]) a[writeIndex] = a[i--];
        else a[writeIndex] = b[j--];

        writeIndex--;
    }
}

vector<int> MergeTwoSortedArraysWrapper(vector<int> A, int m,
                                        const vector<int> &B, int n) {
    MergeTwoSortedArrays(A, m, B, n);
    return A;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A", "m", "B", "n"};
    return GenericTestMain(
            args, "two_sorted_arrays_merge.cc", "two_sorted_arrays_merge.tsv",
            &MergeTwoSortedArraysWrapper, DefaultComparator{}, param_names);
}
