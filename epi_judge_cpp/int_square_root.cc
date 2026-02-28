#include "test_framework/generic_test.h"

int SquareRoot(int k) {
    if (k < 0) return -1;
    int left = -1;
    int right = 1e9;
    while (left + 1 < right) {
        int candidate = left + (right - left) / 2;
        long long sq = (long long) candidate * candidate;
        if (sq > k) {
            right = candidate;
        } else {
            left = candidate;
        }
    }
    return left;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"k"};
    return GenericTestMain(args, "int_square_root.cc", "int_square_root.tsv",
                           &SquareRoot, DefaultComparator{}, param_names);
}
