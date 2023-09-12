#include <string>

#include "test_framework/generic_test.h"

using std::string;

string LookAndSay(int n) {
    string result = "1";
    while (n-- > 1) {
        string temp;
        char prev = result[0];
        int prevIndex = 0;

        for (int i = 1; i < result.size(); i++) {
            if (result[i] != prev) {
                string count = std::to_string(i - prevIndex);
                for (char c: count) {
                    temp.push_back(c);
                }

                temp.push_back(prev);

                prevIndex = i;
                prev = result[i];
            }
        }

        string count = std::to_string(result.size() - prevIndex);
        for (char c: count) {
            temp.push_back(c);
        }

        temp.push_back(prev);

        result = temp;
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"n"};
    return GenericTestMain(args, "look_and_say.cc", "look_and_say.tsv",
                           &LookAndSay, DefaultComparator{}, param_names);
}
