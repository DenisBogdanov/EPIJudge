#include "test_framework/generic_test.h"

double SquareRoot(double x) {
    if (x < 0) return -1;
    if (x == 0) return x;
    long double left = 0.0;
    long double right = 1e18;
    while (right - left > 1e-8) {
        long double candidate = (left + right) / 2;
        if (candidate * candidate > x) {
            right = candidate;
        } else {
            left = candidate;
        }
    }

    return left;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"x"};
    return GenericTestMain(args, "real_square_root.cc", "real_square_root.tsv",
                           &SquareRoot, DefaultComparator{}, param_names);
}
