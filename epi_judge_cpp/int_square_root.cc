#include "test_framework/generic_test.h"

int SquareRoot(int k) {
    if (k < 0) return -1;
    if (k <= 1) return k;

    long long left = 0;
    long long right = k;

    while (left + 1 < right) {
        long long candidate = left + (right - left) / 2;

        if (candidate * candidate <= k) {
            left = candidate;
        } else {
            right = candidate;
        }
    }

    return (int) left;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"k"};
    return GenericTestMain(args, "int_square_root.cc", "int_square_root.tsv",
                           &SquareRoot, DefaultComparator{}, param_names);
}
