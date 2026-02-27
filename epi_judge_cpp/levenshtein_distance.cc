#include <string>
#include <vector>

#include "test_framework/generic_test.h"
using namespace std;

int LevenshteinDistance(const string &a, const string &b) {
    vector dp(a.size() + 1, vector<int>(b.size() + 1));
    for (int i = 0; i <= a.size(); i++) dp[i][0] = i;
    for (int j = 0; j <= b.size(); j++) dp[0][j] = j;
    for (int i = 0; i < a.size(); i++) {
        for (int j = 0; j < b.size(); j++) {
            if (a[i] == b[j]) {
                dp[i + 1][j + 1] = dp[i][j];
            } else {
                // dp[i + 1][j + 1] = 1 + dp[i][j];
                // if (i > 0) dp[i + 1][j + 1] = min(dp[i + 1][j + 1], 1 + dp[i][j + 1]);
                // if (j > 0) dp[i + 1][j + 1] = min(dp[i + 1][j + 1], 1 + dp[i + 1][j]);
                dp[i + 1][j + 1] = 1 + min(
                    dp[i][j],
                    min(dp[i][j + 1], dp[i + 1][j]));
            }
        }
    }

    // for (auto &row : dp) {
    //     for (auto cell : row) {
    //         cout << cell << ' ';
    //     }
    //     cout << '\n';
    // }

    return dp[a.size()][b.size()];
}

int main(int argc, char *argv[]) {
    // cout << LevenshteinDistance("mabde", "abcef") << '\n';
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A", "B"};
    return GenericTestMain(args, "levenshtein_distance.cc",
                           "levenshtein_distance.tsv", &LevenshteinDistance,
                           DefaultComparator{}, param_names);
}
