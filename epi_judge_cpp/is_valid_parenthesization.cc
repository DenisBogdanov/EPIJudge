#include <string>
#include <unordered_map>

#include "test_framework/generic_test.h"

using namespace std;

unordered_map<char, char> closed_to_opened_map{
        {')', '('},
        {']', '['},
        {'}', '{'},
};

bool IsWellFormed(const string &s) {
    deque<char> stack;
    for (char c : s) {
        switch (c) {
            case '(':
            case '[':
            case '{':
                stack.push_front(c);
                break;
            default:
                char opened = closed_to_opened_map[c];
                if (stack.empty() || stack.front() != opened) return false;
                stack.pop_front();
                break;
        }
    }
    return stack.empty();
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"s"};
    return GenericTestMain(args, "is_valid_parenthesization.cc",
                           "is_valid_parenthesization.tsv", &IsWellFormed,
                           DefaultComparator{}, param_names);
}
