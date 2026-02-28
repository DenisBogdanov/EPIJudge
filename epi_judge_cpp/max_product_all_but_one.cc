#include <vector>

#include "test_framework/generic_test.h"
using namespace std;

int FindBiggestNMinusOneProduct(const vector<int> &nums) {
    vector<int> left_products(nums.size() + 1);
    left_products[0] = 1;
    for (int i = 0; i < nums.size(); i++) {
        left_products[i + 1] = left_products[i] * nums[i];
    }
    int ans = 0;
    int right_prod = 1;
    for (int i = nums.size() - 1; i >= 0; i--) {
        ans = max(ans, right_prod * left_products[i]);
        right_prod *= nums[i];
    }
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A"};
    return GenericTestMain(
        args, "max_product_all_but_one.cc", "max_product_all_but_one.tsv",
        &FindBiggestNMinusOneProduct, DefaultComparator{}, param_names);
}
