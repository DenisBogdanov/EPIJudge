#include <string>

#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"

using namespace std;

string IntToString(int x) {
    if (x == 0) return "0";
    bool isNegative = x < 0;
    long long num = abs((long long) x);

    string result;
    while (num > 0) {
        result += '0' + num % 10;
        num /= 10;
    }


    reverse(result.begin(), result.end());
    cout << result << endl;

    return isNegative ? "-" + result : result;
}

int StringToInt(const string &s) {
    int num = 0;
    int start = 0;
    bool isNegative = false;
    if (s[0] == '-') {
        start = 1;
        isNegative = true;
    } else if (s[0] == '+') {
        start = 1;
    }

    for (int i = start; i < s.size(); ++i) {
        num *= 10;
        num += s[i] - '0';
    }

    return isNegative ? -1 * num : num;
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
