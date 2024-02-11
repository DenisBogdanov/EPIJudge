#include "test_framework/generic_test.h"

int Divide(int x, int y) {
    int result = 0;

    int shift = 32;
    unsigned long long yPow = static_cast<unsigned long long>(y) << shift;

    while (x >= y) {
        while (yPow > x) {
            yPow >>= 1;
            --shift;
        }

        result += 1LL << shift;
        x -= yPow;
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"x", "y"};
    return GenericTestMain(args, "primitive_divide.cc", "primitive_divide.tsv",
                           &Divide, DefaultComparator{}, param_names);
}
