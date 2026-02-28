#include <vector>

#include "test_framework/generic_test.h"
using namespace std;

int SmallestNonconstructibleValue(vector<int> nums) {
    sort(nums.begin(), nums.end());
    if (nums.empty() || nums[0] != 1) return 1;
    int curr_sum = 1;
    for (int i = 1; i < nums.size(); i++) {
        if (curr_sum + 1 < nums[i]) break;
        curr_sum += nums[i];
    }
    return curr_sum + 1;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A"};
    return GenericTestMain(args, "smallest_nonconstructible_value.cc",
                           "smallest_nonconstructible_value.tsv",
                           &SmallestNonconstructibleValue, DefaultComparator{},
                           param_names);
}
