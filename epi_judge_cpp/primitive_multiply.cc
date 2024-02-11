#include "test_framework/generic_test.h"

unsigned long long int Add(unsigned long long int x, unsigned long long int y);

unsigned long long Multiply(unsigned long long x, unsigned long long y) {
    unsigned long long result = 0;

    while (x) {
        if (x & 1) {
            result = Add(result, y);
        }

        x >>= 1;
        y <<= 1;
    }

    return result;
}

unsigned long long int Add(unsigned long long int x, unsigned long long int y) {
    unsigned long long result = 0;
    unsigned long long carry = 0;
    unsigned long long k = 1;

    int shift = 0;

    while ((x >> shift) || (y >> shift)) {
        auto xk = x & k;
        auto yk = y & k;

        auto tempCarry = (xk & yk) | (xk & carry) | (yk & carry);
        result |= xk ^ yk ^ carry;

        carry = tempCarry << 1;
        k <<= 1;
        shift++;
    }

    return result | carry;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"x", "y"};
    return GenericTestMain(args, "primitive_multiply.cc",
                           "primitive_multiply.tsv", &Multiply,
                           DefaultComparator{}, param_names);
}
