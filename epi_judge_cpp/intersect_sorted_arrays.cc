#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<int> IntersectTwoSortedArrays(const vector<int> &a, const vector<int> &b) {
    vector<int> result;

    int j = 0;
    for (int i = 0; i < a.size(); ++i) {
        if (i > 0 && a[i] == a[i - 1]) continue;
        while (j < b.size() && b[j] < a[i]) {
            j++;
        }

        if (j == b.size()) break;
        if (b[j] == a[i]) result.push_back(a[i]);
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A", "B"};
    return GenericTestMain(
            args, "intersect_sorted_arrays.cc", "intersect_sorted_arrays.tsv",
            &IntersectTwoSortedArrays, DefaultComparator{}, param_names);
}
