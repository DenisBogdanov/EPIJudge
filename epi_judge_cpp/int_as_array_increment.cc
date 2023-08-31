#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<int> PlusOne(vector<int> arr) {
    int carry = 1;
    for (int i = arr.size() - 1; i >= 0; --i) {
        int next = carry + arr[i];
        if (next == 10) {
            carry = 1;
            arr[i] = 0;
        } else {
            arr[i] = next;
            return arr;
        }
    }

    arr.insert(arr.begin(), carry);
    return arr;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A"};
    return GenericTestMain(args, "int_as_array_increment.cc",
                           "int_as_array_increment.tsv", &PlusOne,
                           DefaultComparator{}, param_names);
}
