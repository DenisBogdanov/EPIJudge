#include "test_framework/generic_test.h"

short Parity(unsigned long long x) {
    short ans = 0;
    while (x) {
        ans ^= 1;
        x &= x - 1;
    }
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"x"};
    return GenericTestMain(args, "parity.cc", "parity.tsv", &Parity,
                           DefaultComparator{}, param_names);
}
