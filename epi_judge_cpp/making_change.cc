#include "test_framework/generic_test.h"

int ChangeMaking(int cents) {
    int count = 0;
    std::vector<int> coins = {100, 50, 25, 10, 5, 1};

    for (auto coin : coins) {
        count += cents / coin;
        cents %= coin;
    }

    return count;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"cents"};
    return GenericTestMain(args, "making_change.cc", "making_change.tsv",
                           &ChangeMaking, DefaultComparator{}, param_names);
}
