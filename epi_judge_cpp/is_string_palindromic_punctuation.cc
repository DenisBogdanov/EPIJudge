#include <string>

#include "test_framework/generic_test.h"

using std::string;

bool IsPalindrome(const string &s) {
    int left = 0;
    int right = s.size() - 1;

    while (left < right) {
        while (left < right && !isalnum(s[left])) {
            left++;
        }

        while (left < right && !isalnum(s[right])) {
            right--;
        }

        if (left >= right) {
            break;
        }

        if (tolower(s[left]) != tolower(s[right])) {
            return false;
        }

        ++left;
        --right;
    }

    return true;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"s"};
    return GenericTestMain(args, "is_string_palindromic_punctuation.cc",
                           "is_string_palindromic_punctuation.tsv", &IsPalindrome,
                           DefaultComparator{}, param_names);
}
