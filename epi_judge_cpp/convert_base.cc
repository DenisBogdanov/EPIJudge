#include <string>

#include "test_framework/generic_test.h"

using std::string;

string ConvertBase(const string &num_as_string, int b1, int b2) {
    if (num_as_string == "0") return "0";
    long long num = 0;
    bool is_negative = false;
    for (char c : num_as_string) {
        if (c == '-') {
            is_negative = true;
            continue;
        }
        num *= b1;
        if (c >= 'A') {
            num += 10 + (c - 'A');
        } else {
            num += c - '0';
        }
    }

    string ans;
    while (num) {
        long long rem = num % b2;
        if (rem < 10) {
            ans.push_back('0' + rem);
        } else {
            ans.push_back('A' + (rem - 10));
        }
        num /= b2;
    }
    if (is_negative) ans.push_back('-');
    std::reverse(ans.begin(), ans.end());
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"num_as_string", "b1", "b2"};
    return GenericTestMain(args, "convert_base.cc", "convert_base.tsv",
                           &ConvertBase, DefaultComparator{}, param_names);
}
