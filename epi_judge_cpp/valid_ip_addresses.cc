#include <algorithm>
#include <string>
#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

vector<string> GetValidIpAddress(const string &s) {
    vector<string> result;
    if (s.size() > 12 || s.size() < 4) return result;

    for (int i = 0; i < 3; ++i) {
        if (i >= s.size() - 3) break;
        int len = i + 1;
        int a = stoi(s.substr(0, len));
        if (s[0] == '0' && len > 1) break;
        if (a > 255) break;

        for (int j = i + 1; j < i + 4; ++j) {
            if (j >= s.size() - 2) break;
            len = j - (i + 1) + 1;
            int b = stoi(s.substr(i + 1, len));
            if (s[i + 1] == '0' && len > 1) break;
            if (b > 255) break;
            
            for (int k = j + 1; k < j + 4; ++k) {
                if (k >= s.size() - 1) break;
                len = k - (j + 1) + 1;
                int c = stoi(s.substr(j + 1, len));
                if (s[j + 1] == '0' && len > 1) break;
                if (c > 255) break;

                len = s.size() - k - 1;
                int d = stoi(s.substr(k + 1));
                if (s[k + 1] == '0' && len > 1) continue;
                if (d > 255) continue;
                
                result.push_back(to_string(a) + '.' + to_string(b) + '.' + to_string(c) + '.' + to_string(d));
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
