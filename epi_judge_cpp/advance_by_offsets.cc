#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

bool CanReachEnd(const vector<int> &max_advance_steps) {
    int currMaxPosition = 0;
    for (int i = 0; i < max_advance_steps.size(); ++i) {
        if (currMaxPosition < i) return false;
        if (currMaxPosition + 1 >= max_advance_steps.size()) return true;

        currMaxPosition = std::max(currMaxPosition, i + max_advance_steps[i]);
    }

    return true;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"max_advance_steps"};
    return GenericTestMain(args, "advance_by_offsets.cc",
                           "advance_by_offsets.tsv", &CanReachEnd,
                           DefaultComparator{}, param_names);
}
