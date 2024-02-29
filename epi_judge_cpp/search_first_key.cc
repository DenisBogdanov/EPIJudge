#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

int SearchFirstOfK(const vector<int> &v, int k) {
    if (v.empty()) return -1;

    int left = -1;
    int right = v.size();

    while (left + 1 < right) {
        int mid = left + (right - left) / 2;

        if (v[mid] < k) {
            left = mid;
        } else {
            right = mid;
        }
    }

    return v[left + 1] == k ? left + 1 : -1;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A", "k"};
    return GenericTestMain(args, "search_first_key.cc", "search_first_key.tsv",
                           &SearchFirstOfK, DefaultComparator{}, param_names);
}
