#include <string>

#include "test_framework/generic_test.h"

using std::string;

string ConvertBase(const string &num_as_string, int b1, int b2) {
    if (num_as_string == "0") return num_as_string;

    string sign;

    long long num10 = 0;
    long long pow = 1;

    for (int i = num_as_string.size() - 1; i >= 0; i--) {
        if (i == 0) {
            if (num_as_string[i] == '-') {
                sign = "-";
                break;
            } else if (num_as_string[i] == '+') {
                break;
            }
        }

        if (num_as_string[i] >= 'A') {
            num10 += (num_as_string[i] - 'A' + 10) * pow;
        } else {
            num10 += (num_as_string[i] - '0') * pow;
        }

        pow *= b1;
    }

    string result;

    while (num10 > 0) {
        long long mod = num10 % b2;
        if (mod < 10) {
            result.push_back('0' + mod);
        } else {
            result.push_back('A' + mod - 10);
        }

        num10 /= b2;
    }

    if (sign.size() == 1) {
        result.push_back(sign[0]);
    }

    std::reverse(result.begin(), result.end());
    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"num_as_string", "b1", "b2"};
    return GenericTestMain(args, "convert_base.cc", "convert_base.tsv",
                           &ConvertBase, DefaultComparator{}, param_names);
}
