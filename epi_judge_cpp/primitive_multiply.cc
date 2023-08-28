#include "test_framework/generic_test.h"

unsigned long long Add(unsigned long long x, unsigned long long y);

unsigned long long Multiply(unsigned long long x, unsigned long long y) {
    unsigned long long result = 0;

    while (y) {
        if (y & 1) {
            result = Add(result, x);
        }

        x <<= 1;
        y >>= 1;
    }

    return result;
}

unsigned long long Add(unsigned long long x, unsigned long long y) {
    unsigned long long result = 0;
    unsigned long long carry = 0;
    unsigned long long shift = 1;

    while (shift <= x || shift <= y) {
        unsigned long long xBit = x & shift;
        unsigned long long yBit = y & shift;

        result |= (xBit ^ yBit ^ carry);
        carry = ((xBit & yBit) | (xBit & carry) | (yBit & carry)) << 1;

        shift <<= 1;
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
