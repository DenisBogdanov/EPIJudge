#include "test_framework/generic_test.h"
unsigned long long ReverseBits(unsigned long long x) {
    int left = 0;
    int right = 63;
    while (left < right) {
        if (((x >> left) & 1) != ((x >> right) & 1)) {
            x ^= (1LL << left) | (1LL << right);
        }
        left++;
        right--;
    }
    return x;
}

int main(int argc, char* argv[]) {
  std::vector<std::string> args{argv + 1, argv + argc};
  std::vector<std::string> param_names{"x"};
  return GenericTestMain(args, "reverse_bits.cc", "reverse_bits.tsv",
                         &ReverseBits, DefaultComparator{}, param_names);
}
