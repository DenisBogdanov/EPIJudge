#include <string>

#include "test_framework/generic_test.h"

using std::string;

string LookAndSay(int n) {
    if (n == 0) return "";
    string prev = "1";
    for (int i = 1; i < n; ++i) {
        string next;

        char curr = prev[0];
        int count = 1;
        for (int j = 1; j < prev.size(); ++j) {
            if (prev[j] != prev[j - 1]) {
                next += std::to_string(count);
                next += curr;
                count = 1;
                curr = prev[j];
            } else {
                count++;
            }
        }

        next += std::to_string(count);
        next += curr;
        prev = next;
    }

    return prev;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"n"};
    return GenericTestMain(args, "look_and_say.cc", "look_and_say.tsv",
                           &LookAndSay, DefaultComparator{}, param_names);
}
