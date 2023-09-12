#include <algorithm>
#include <string>
#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

vector<string> GetValidIpAddress(const string &s) {
    vector<string> result;
    int n = s.size();

    if (n < 4 || n > 12) return result;

    for (int a = 0; a < min(3, n - 3); ++a) {
        int first = stoi(s.substr(0, a + 1));
        if (s[0] == '0' && a + 1 > 1) break;
        if (first > 255) break;

        for (int b = a + 1; b < min(a + 4, n - 2); ++b) {
            int second = stoi(s.substr(a + 1, b - a));
            if (s[a + 1] == '0' && b - a > 1) break;
            if (second > 255) break;

            for (int c = b + 1; c < min(b + 4, n - 1); ++c) {
                int third = stoi(s.substr(b + 1, c - b));
                if (s[b + 1] == '0' && c - b > 1) break;
                if (third > 255) break;

                int fourth = stoi(s.substr(c + 1, n - c - 1));
                if (s[c + 1] == '0' && n - c - 1 > 1) continue;
                if (fourth > 255) continue;

                result.push_back(to_string(first) +
                                 "." + to_string(second) +
                                 "." + to_string(third) +
                                 "." + to_string(fourth));
            }
        }
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"s"};
    return GenericTestMain(args, "valid_ip_addresses.cc",
                           "valid_ip_addresses.tsv", &GetValidIpAddress,
                           UnorderedComparator{}, param_names);
}
