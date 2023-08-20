#include "test_framework/generic_test.h"

long long Gcd(long long x, long long y) {
    while (y) {
        long long temp = x;
        x = y;
        y = temp % y;
    }

    return x;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"x", "y"};
    return GenericTestMain(args, "gcd.cc", "gcd.tsv", &Gcd, DefaultComparator{},
                           param_names);
}
