#include <string>

#include "test_framework/generic_test.h"

using namespace std;

int RomanToInteger(const string &s) {
    map<char, int> symbolToValueMap = {
            {'I', 1},
            {'V', 5},
            {'X', 10},
            {'L', 50},
            {'C', 100},
            {'D', 500},
            {'M', 1000},
    };

    vector<int> values;
    for (char c: s) {
        values.push_back(symbolToValueMap[c]);
    }

    int currMin = 0;
    int result = 0;

    for (int i = values.size() - 1; i >= 0; i--) {
        if (values[i] >= currMin) {
            result += values[i];
            currMin = values[i];
        } else {
            result -= values[i];
        }
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"s"};
    return GenericTestMain(args, "roman_to_integer.cc", "roman_to_integer.tsv",
                           &RomanToInteger, DefaultComparator{}, param_names);
}
