#include "test_framework/generic_test.h"

double SquareRoot(double x) {
    if (x < 0) return -1;

    double left = 0.0;
    double right = x + 1;

    while (right - left > 1e-8) {
        double candidate = (left + right) / 2;

        if (candidate * candidate < x) {
            left = candidate;
        } else {
            right = candidate;
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
