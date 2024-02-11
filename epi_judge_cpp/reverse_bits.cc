#include "test_framework/generic_test.h"

unsigned long long int swap_bits(unsigned long long int x, int i, int j);

unsigned long long ReverseBits(unsigned long long x) {
    int left = 0;
    int right = 63;

    while (left < right) {
        x = swap_bits(x, left, right);
        left++;
        right--;
    }

    return x;
}

unsigned long long int swap_bits(unsigned long long int x, int i, int j) {
    if (((x >> i) & 1) == ((x >> j) & 1)) return x;
    return x ^ ((1ULL << i) | (1ULL << j));
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"x"};
    return GenericTestMain(args, "reverse_bits.cc", "reverse_bits.tsv",
                           &ReverseBits, DefaultComparator{}, param_names);
}
