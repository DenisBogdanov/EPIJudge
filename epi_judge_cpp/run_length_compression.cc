#include <string>

#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"

using std::string;

string Decoding(const string &s) {
    if (s.empty()) return s;

    string result;
    int count = 0;
    for (int i = 0; i < s.size(); ++i) {
        if (isdigit(s[i])) {
            count *= 10;
            count += s[i] - '0';
        } else {
            for (int j = 0; j < count; ++j) {
                result += s[i];
            }

            count = 0;
        }
    }

    for (int j = 0; j < count; ++j) {
        result += s.back();
    }

    return result;
}

string Encoding(const string &s) {
    if (s.empty()) return s;
    string result;
    int count = 1;

    for (int i = 1; i < s.size(); ++i) {
        if (s[i] == s[i - 1]) {
            ++count;
        } else {
            result += std::to_string(count);
            result += s[i - 1];
            count = 1;
        }
    }

    result += std::to_string(count);
    result += s.back();

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
