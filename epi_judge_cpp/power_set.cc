#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<vector<int>> GeneratePowerSet(const vector<int>& input_set) {
    vector<vector<int>> result;

    size_t n = input_set.size();
    vector<int> curr;
    for (int mask = 0; mask != (1 << n); ++mask) {
        curr.clear();
        for (int i = 0; i != n; ++i) {
            if (mask & (1 << i)) {
                curr.push_back(input_set[i]);
            }
        }

        result.emplace_back(curr);
    }
    
    return result;
}

int main(int argc, char* argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"input_set"};
    return GenericTestMain(args, "power_set.cc", "power_set.tsv",
                           &GeneratePowerSet, UnorderedComparator{}, param_names);
}
