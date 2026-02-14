#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<int> PlusOne(vector<int> nums) {
    int carry = 1;
    for (int i = nums.size() - 1; i >= 0; i--) {
        int sum = nums[i] + carry;
        carry = sum / 10;
        nums[i] = sum % 10;
        if (carry == 0) break;
    }
    if (carry == 1) {
        nums.insert(nums.begin(), 1);
    }
    return nums;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A"};
    return GenericTestMain(args, "int_as_array_increment.cc",
                           "int_as_array_increment.tsv", &PlusOne,
                           DefaultComparator{}, param_names);
}
