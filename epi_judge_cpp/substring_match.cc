#include <string>

#include "test_framework/generic_test.h"

using std::string;

bool eq(const string &t, const string &s, int i);

// Returns the index of the first character of the substring if found, -1
// otherwise.
int RabinKarp(const string &t, const string &s) {
    if (s.size() > t.size()) return -1;

    int prime = 37;
    long long tHash = 0;
    long long sHash = 0;
    long long mult = 1;

    for (int i = 0; i < s.size(); ++i) {
        tHash = tHash * prime + t[i];
        sHash = sHash * prime + s[i];

        if (i != 0) {
            mult *= prime;
        }
    }

    if (tHash == sHash) {
        if (eq(t, s, 0)) {
            return 0;
        }
    }

    for (int i = 1; i + s.size() <= t.size(); ++i) {
        tHash -= t[i - 1] * mult;
        tHash *= prime;
        tHash += t[i + s.size() - 1];

        if (tHash == sHash && eq(t, s, i)) {
            return i;
        }
    }

    return -1;
}

bool eq(const string &t, const string &s, int start) {
    for (int i = 0; i < s.size(); ++i) {
        if (t[i + start] != s[i]) return false;
    }

    return true;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"t", "s"};
    return GenericTestMain(args, "substring_match.cc", "substring_match.tsv",
                           &RabinKarp, DefaultComparator{}, param_names);
}
