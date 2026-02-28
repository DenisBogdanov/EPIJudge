#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

bool HasThreeSum(vector<int> nums, int t) {
    sort(nums.begin(), nums.end());
    for (int start = 0; start < nums.size(); start++) {
        int left = start;
        int right = nums.size() - 1;
        while (left <= right) {
            int sum = nums[start] + nums[left] + nums[right];
            if (sum > t) {
                right--;
            } else if (sum < t) {
                left++;
            } else {
                return  true;
            }
        }
    }

    return false;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A", "t"};
    return GenericTestMain(args, "three_sum.cc", "three_sum.tsv", &HasThreeSum,
                           DefaultComparator{}, param_names);
}
