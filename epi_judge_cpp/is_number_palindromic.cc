#include "test_framework/generic_test.h"

bool IsPalindromeNumber(int x) {
    if (x < 0) return false;

    int len = static_cast<int>(floor(log10(x))) + 1;
    int mask = static_cast<int>(pow(10, len - 1));

    for (int shift = 0; shift < len / 2; ++shift) {
        if (x / mask != x % 10) return false;

        x %= mask;
        x /= 10;
        mask /= 100;
    }

    return true;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"x"};
    return GenericTestMain(args, "is_number_palindromic.cc",
                           "is_number_palindromic.tsv", &IsPalindromeNumber,
                           DefaultComparator{}, param_names);
}
