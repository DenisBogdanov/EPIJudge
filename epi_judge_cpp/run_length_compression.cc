#include <string>

#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"

using std::string;

string Decoding(const string &s) {
    int count = 0;
    string result;

    for (char i: s) {
        if (i >= '0' && i <= '9') {
            count *= 10;
            count += i - '0';
        } else {
            for (int j = 0; j < count; ++j) {
                result.push_back(i);
            }
            count = 0;
        }
    }

    return result;
}

string Encoding(const string &s) {
    int count = 1;
    string result;

    for (int i = 1; i < s.size(); i++) {
        if (s[i] == s[i - 1]) {
            count++;
        } else {
            result += std::to_string(count);
            result.push_back(s[i - 1]);
            count = 1;
        }
    }

    result += std::to_string(count);
    result.push_back(s.back());

    return result;
}

void RleTester(const string &encoded, const string &decoded) {
    if (Decoding(encoded) != decoded) {
        throw TestFailure("Decoding failed");
    }
    if (Encoding(decoded) != encoded) {
        throw TestFailure("Encoding failed");
    }
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"encoded", "decoded"};
    return GenericTestMain(args, "run_length_compression.cc",
                           "run_length_compression.tsv", &RleTester,
                           DefaultComparator{}, param_names);
}
