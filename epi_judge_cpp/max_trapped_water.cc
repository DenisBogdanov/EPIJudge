#include <vector>

#include "test_framework/generic_test.h"
using namespace std;

int GetMaxTrappedWater(const vector<int> &heights) {
    int left = 0;
    int right = heights.size() - 1;
    int ans = 0;
    while (left < right) {
        ans = max(ans, (right - left) * min(heights[left], heights[right]));
        if (heights[left] < heights[right]) left++;
        else right--;
    }
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"heights"};
    return GenericTestMain(args, "max_trapped_water.cc", "max_trapped_water.tsv",
                           &GetMaxTrappedWater, DefaultComparator{}, param_names);
}
