#include <string>

#include "test_framework/generic_test.h"

using std::string;

// Returns the index of the first character of the substring if found, -1
// otherwise.
int RabinKarp(const string &t, const string &s) {
    if (s.size() > t.size()) return -1;

    int prime = 37;

    int tHash = 0;
    int sHash = 0;
    int mult = 1;

    for (int i = 0; i < s.size(); i++) {
        tHash = tHash * prime + t[i];
        sHash = sHash * prime + s[i];

        if (i > 0) {
            mult *= prime;
        }
    }

    for (int i = s.size(); i < t.size(); ++i) {
        if (tHash == sHash && !t.compare(i - s.size(), s.size(), s)) {
            return i - s.size();
        }

        tHash -= t[i - s.size()] * mult;
        tHash = tHash * prime + t[i];
    }

    if (tHash == sHash && !t.compare(t.size() - s.size(), s.size(), s)) {
        return t.size() - s.size();
    }

    return -1;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"t", "s"};
    return GenericTestMain(args, "substring_match.cc", "substring_match.tsv",
                           &RabinKarp, DefaultComparator{}, param_names);
}
