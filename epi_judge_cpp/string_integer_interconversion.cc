#include <string>

#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"

using std::string;

string IntToString(int x) {
    if (x == 0) return "0";
    long long num = x;

    string sign;

    if (num < 0) {
        num = -num;
        sign = "-";
    }

    string s;
    while (num > 0) {
        s.push_back('0' + num % 10);
        num /= 10;
    }

    std::reverse(s.begin(), s.end());
    return sign + s;
}

int StringToInt(const string &s) {
    int i = 0;
    int sign = 1;
    if (s[0] == '+') i = 1;

    if (s[0] == '-') {
        sign = -1;
        i = 1;
    }

    int result = 0;

    for (; i < s.size(); i++) {
        result *= 10;
        result += s[i] - '0';
    }

    return result * sign;
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
