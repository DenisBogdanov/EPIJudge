#include "test_framework/generic_test.h"

int Fibonacci(int n) {
    if (n < 2) return n;
    int a = 0;
    int b = 1;
    for (int i = 1; i < n; i++) {
        int temp = a;
        a = b;
        b += temp;
    }
    return b;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"n"};
    return GenericTestMain(args, "fibonacci.cc", "fibonacci.tsv", &Fibonacci,
                           DefaultComparator{}, param_names);
}
