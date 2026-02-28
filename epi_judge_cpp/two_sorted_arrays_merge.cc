#include <vector>

#include "test_framework/generic_test.h"
using std::vector;

void MergeTwoSortedArrays(vector<int> &nums1, int m, const vector<int> &nums2, int n) {
    int i1 = m - 1;
    int i2 = n - 1;
    while (i1 >= 0 || i2 >= 0) {
        if (i1 == -1) {
            nums1[i2] = nums2[i2];
            i2--;
        } else if (i2 == -1) {
            break;
        } else if (nums1[i1] >= nums2[i2]) {
            nums1[i1 + i2 + 1] = nums1[i1];
            i1--;
        } else {
            nums1[i1 + i2 + 1] = nums2[i2];
            i2--;
        }
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
