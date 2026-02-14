#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

int SearchFirstOfK(const vector<int> &nums, int k) {
    if (nums.empty()) return -1;
    int left = -1;
    int right = nums.size() - 1;
    while (left + 1 < right) {
        int mid = (left + right) / 2;
        if (nums[mid] < k) {
            left = mid;
        } else {
            right = mid;
        }
    }
    if (nums[right] == k) return right;
    else return -1;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A", "k"};
    return GenericTestMain(args, "search_first_key.cc", "search_first_key.tsv",
                           &SearchFirstOfK, DefaultComparator{}, param_names);
}
