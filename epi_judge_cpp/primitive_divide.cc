#include "test_framework/generic_test.h"

int Divide(int x, int y) {
    int result = 0;

    int dividend = 0;

    for (int shift = 30; shift >= 0; --shift) {
        dividend <<= 1;
        result <<= 1;

        if (x & (1 << shift)) {
            dividend |= 1;
        }

        if (dividend >= y) {
            result |= 1;
            dividend -= y;
        }
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"x", "y"};
    return GenericTestMain(args, "primitive_divide.cc", "primitive_divide.tsv",
                           &Divide, DefaultComparator{}, param_names);
}
