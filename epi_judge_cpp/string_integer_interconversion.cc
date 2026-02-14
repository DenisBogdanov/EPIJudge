#include <string>

#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"

using std::string;

string IntToString(int x) {
    if (x == 0) return "0";
    string ans;
    long long copy = x;
    bool is_negative = false;
    if (copy < 0) {
        is_negative = true;
        copy = abs(copy);
    }
    while (copy) {
        ans.push_back((char) (copy % 10 + '0'));
        copy /= 10;
    }
    if (is_negative) {
        ans.push_back('-');
    }
    std::reverse(ans.begin(), ans.end());
    return ans;
}

int StringToInt(const string &s) {
    long long ans = 0;
    int start = 0;
    int mult = 1;
    if (s[0] == '-') {
        mult = -1;
        start = 1;
    } else if (s[0] == '+') {
        start = 1;
    }
    for (int i = start; i < s.size(); ++i) {
        ans *= 10;
        ans += s[i] - '0';
    }
    return (int) (ans * mult);
}

void Wrapper(int x, const string &s) {
    if (stoi(IntToString(x)) != x) {
        throw TestFailure("Int to string conversion failed");
    }

    if (StringToInt(s) != x) {
        throw TestFailure("String to int conversion failed");
    }
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"x", "s"};
    return GenericTestMain(args, "string_integer_interconversion.cc",
                           "string_integer_interconversion.tsv", &Wrapper,
                           DefaultComparator{}, param_names);
}
