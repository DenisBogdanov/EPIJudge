#include <string>

#include "test_framework/generic_test.h"

using std::string;

string ConvertBase(const string &num_as_string, int b1, int b2) {
    int decimal = 0;
    int start = 0;
    bool isNegative = false;
    if (num_as_string[0] == '-') {
        start = 1;
        isNegative = true;
    } else if (num_as_string[0] == '+') {
        start = 1;
    }

    for (int i = start; i < num_as_string.size(); ++i) {
        decimal *= b1;
        if (num_as_string[i] <= '9') {
            decimal += num_as_string[i] - '0';
        } else {
            decimal += num_as_string[i] - 'A' + 10;
        }
    }

    string result;
    do {
        int rem = decimal % b2;
        if (rem <= 9) {
            result += '0' + rem;
        } else {
            result += 'A' + rem - 10;
        }

        decimal /= b2;
    } while (decimal);

    std::reverse(result.begin(), result.end());

    return isNegative ? "-" + result : result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"num_as_string", "b1", "b2"};
    return GenericTestMain(args, "convert_base.cc", "convert_base.tsv",
                           &ConvertBase, DefaultComparator{}, param_names);
}
