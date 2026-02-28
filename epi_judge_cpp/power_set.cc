#include <vector>

#include "test_framework/generic_test.h"
using std::vector;

vector<vector<int> > GeneratePowerSet(const vector<int> &input_set) {
    int n = input_set.size();
    vector<vector<int>> ans;
    for (int mask = 0; mask < (1 << n); mask++) {
        vector<int> new_set;
        for (int i = 0; i < n; i++) {
            if ((mask >> i) & 1) {
                new_set.push_back(input_set[i]);
            }
        }
        ans.push_back(new_set);
    }
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"input_set"};
    return GenericTestMain(args, "power_set.cc", "power_set.tsv",
                           &GeneratePowerSet, UnorderedComparator{}, param_names);
}
