#include <vector>

#include "test_framework/generic_test.h"
using namespace std;

const int INF = 1e9;

int MinimumPathWeight(const vector<vector<int> > &triangle) {
    vector dp(triangle.size() + 1, 0);
    vector<int> temp(triangle.size() + 1);
    for (const auto &level: triangle) {
        fill(temp.begin(), temp.end(), INF);
        for (int i = 0; i < level.size(); i++) {
            temp[i] = min(temp[i], dp[i] + level[i]);
            temp[i + 1] = min(temp[i + 1], dp[i] + level[i]);
        }
        swap(dp, temp);
    }

    return *std::min_element(dp.begin(), dp.end());
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"triangle"};
    return GenericTestMain(args, "minimum_weight_path_in_a_triangle.cc",
                           "minimum_weight_path_in_a_triangle.tsv",
                           &MinimumPathWeight, DefaultComparator{}, param_names);
}
