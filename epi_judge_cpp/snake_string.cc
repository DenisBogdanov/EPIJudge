#include <string>

#include "test_framework/generic_test.h"

using std::string;

string SnakeString(const string &s) {
    string s1;
    string s2;
    string s3;

    for (int i = 0; i < s.size(); i++) {
        if (i % 4 == 1) {
            s1 += s[i];
        } else if (i % 4 == 3) {
            s3 += s[i];
        } else {
            s2 += s[i];
        }
    }
    return s1 + s2 + s3;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"s"};
    return GenericTestMain(args, "snake_string.cc", "snake_string.tsv",
                           &SnakeString, DefaultComparator{}, param_names);
}
