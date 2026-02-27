#include <vector>

#include "test_framework/generic_test.h"
using namespace std;

int helper(const vector<int> &coins, int left, int right, vector<vector<int>> &dp) {
    if (left > right) return 0;
    if (dp[left][right] != -1) return dp[left][right];
    int ans = max(
        coins[left] - helper(coins, left + 1, right, dp),
        coins[right] - helper(coins, left, right - 1, dp));
    return dp[left][right] = ans;
}

int MaximumRevenue(const vector<int> &coins) {
    int total = 0;
    for (int coin : coins) total += coin;
    vector dp(coins.size() + 1, vector(coins.size() + 1, -1));
    int diff = helper(coins, 0, coins.size() - 1, dp);
    return (diff + total) / 2;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"coins"};
    return GenericTestMain(args, "picking_up_coins.cc", "picking_up_coins.tsv",
                           &MaximumRevenue, DefaultComparator{}, param_names);
}
