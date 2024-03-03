#include <vector>

#include "test_framework/generic_test.h"

using std::vector;
using std::sort;

int SmallestNonconstructibleValue(vector<int> v) {
    std::sort(v.begin(), v.end());
    int sum = 0;
    for (int num: v) {
        if (num > sum + 1) {
            break;
        }

        sum += num;
    }

    return sum + 1;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A"};
    return GenericTestMain(args, "smallest_nonconstructible_value.cc",
                           "smallest_nonconstructible_value.tsv",
                           &SmallestNonconstructibleValue, DefaultComparator{},
                           param_names);
}
