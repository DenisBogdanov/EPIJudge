#include "test_framework/generic_test.h"

void SwapBits(unsigned long long int *x, int i, int j);

unsigned long long ReverseBits(unsigned long long x) {
    for (int i = 0; i < 32; ++i) {
        SwapBits(&x, i, 64 - i - 1);
    }

    return x;
}

void SwapBits(unsigned long long int *x, int i, int j) {
    if (((*x >> i) & 1) == ((*x >> j) & 1)) return;
    *x ^= 1LL << i | 1LL << j;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"x"};
    return GenericTestMain(args, "reverse_bits.cc", "reverse_bits.tsv",
                           &ReverseBits, DefaultComparator{}, param_names);
}
