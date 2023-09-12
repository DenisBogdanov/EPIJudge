#include <string>

#include "test_framework/generic_test.h"

using namespace std;

string SnakeString(const string &s) {
    vector<string> result(3);
    for (int i = 0; i < s.size(); i++) {
        if (i % 2 == 0) result[1].push_back(s[i]);
        else if (i % 4 == 1) result[0].push_back(s[i]);
        else result[2].push_back(s[i]);
    }

    return result[0] + result[1] + result[2];
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"s"};
    return GenericTestMain(args, "snake_string.cc", "snake_string.tsv",
                           &SnakeString, DefaultComparator{}, param_names);
}
