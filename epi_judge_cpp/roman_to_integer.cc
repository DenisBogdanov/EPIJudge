#include <string>

#include "test_framework/generic_test.h"

using std::string;
using std::min;

int RomanToInteger(const string &s) {
    int currMin = 1'000;
    int result = 0;

    for (auto c : s) {
        switch (c) {
            case 'M':
                result += 1'000;
                if (currMin == 100) {
                    result -= 200;
                }
                break;
            case 'D':
                result += 500;
                if (currMin == 100) {
                    result -= 200;
                }
                currMin = min(currMin, 500);
                break;
            case 'C':
                result += 100;
                if (currMin == 10) {
                    result -= 20;
                }
                currMin = min(currMin, 100);
                break;
            case 'L':
                result += 50;
                if (currMin == 10) {
                    result -= 20;
                }
                currMin = min(currMin, 50);
                break;
            case 'X':
                result += 10;
                if (currMin == 1) {
                    result -= 2;
                }
                currMin = min(currMin, 10);
                break;
            case 'V':
                result += 5;
                if (currMin == 1) {
                    result -= 2;
                }
                currMin = min(currMin, 5);
                break;
            case 'I':
                result += 1;
                currMin = min(currMin, 1);
                break;
            default:
                break;
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
