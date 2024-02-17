#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

bool CanReachEnd(const vector<int> &max_advance_steps) {
    size_t currMax = 1;
    for (size_t i = 0; i < std::min(currMax, max_advance_steps.size()); ++i) {
        if (currMax >= max_advance_steps.size()) return true;

        currMax = std::max(currMax, i + max_advance_steps[i] + 1);
    }

    return false;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"max_advance_steps"};
    return GenericTestMain(args, "advance_by_offsets.cc",
                           "advance_by_offsets.tsv", &CanReachEnd,
                           DefaultComparator{}, param_names);
}
