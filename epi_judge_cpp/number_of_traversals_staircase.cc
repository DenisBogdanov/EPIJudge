#include "test_framework/generic_test.h"

using namespace std;

int NumberOfWaysToTop(int top, int maximum_step) {
    vector<int> dp(top + 1, 0);
    dp[0] = 1;
    for (int curr = 1; curr <= top; curr++) {
        for (int step = 1; step <= maximum_step; step++) {
            if (curr < step) break;
            dp[curr] += dp[curr - step];
        }
    }
    return dp[top];
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"top", "maximum_step"};
    return GenericTestMain(args, "number_of_traversals_staircase.cc",
                           "number_of_traversals_staircase.tsv",
                           &NumberOfWaysToTop, DefaultComparator{}, param_names);
}
