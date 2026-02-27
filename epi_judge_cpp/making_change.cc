#include "test_framework/generic_test.h"

const int COINS[] = {100, 50, 25, 10, 5, 1};

int ChangeMaking(int cents) {
    int result = 0;
    for (int coin : COINS) {
        result += cents / coin;
        cents %= coin;
    }
    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"cents"};
    return GenericTestMain(args, "making_change.cc", "making_change.tsv",
                           &ChangeMaking, DefaultComparator{}, param_names);
}
