#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

vector<int> IntersectTwoSortedArrays(const vector<int> &a, const vector<int> &b) {
    set<int> unique;
    if (a.size() > b.size()) {
        for (int num: b) {
            if (std::binary_search(a.begin(), a.end(), num)) {
                unique.insert(num);
            }
        }
    } else {
        for (int num: a) {
            if (std::binary_search(b.begin(), b.end(), num)) {
                unique.insert(num);
            }
        }
    }
    vector<int> ans(unique.begin(), unique.end());
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A", "B"};
    return GenericTestMain(
            args, "intersect_sorted_arrays.cc", "intersect_sorted_arrays.tsv",
            &IntersectTwoSortedArrays, DefaultComparator{}, param_names);
}
