#include "test_framework/generic_test.h"

using namespace std;

int ComputeBinomialCoefficient(int n, int k) {
    // 1: 1
    // 2: 1 2 1
    // 3: 1 3 3 1
    // 4: 1 4 6 4 1
    vector dp(n + 1, vector<int>(k + 1, 0));
    dp[0][0] = 1;
    for (int level = 1; level <= n; level++) {
        dp[level][0] = 1;
        for (int i = 1; i <= k; i++) {
            dp[level][i] = dp[level - 1][i - 1] + dp[level - 1][i];
        }
    }
    return dp[n][k];
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"n", "k"};
    return GenericTestMain(
        args, "binomial_coefficients.cc", "binomial_coefficients.tsv",
        &ComputeBinomialCoefficient, DefaultComparator{}, param_names);
}
