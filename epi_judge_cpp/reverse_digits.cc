#include "test_framework/generic_test.h"

long long Reverse(int x) {
    int multiplier = x < 0 ? -1 : 1;
    x = abs(x);

    long long result = 0;
    while (x) {
        result *= 10;
        result += x % 10;
        x /= 10;
    }

    return result * multiplier;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"x"};
    return GenericTestMain(args, "reverse_digits.cc", "reverse_digits.tsv",
                           &Reverse, DefaultComparator{}, param_names);
}
