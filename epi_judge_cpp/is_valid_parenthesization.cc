#include <string>

#include "test_framework/generic_test.h"

using std::string;

bool IsWellFormed(const string &s) {
    std::stack<char> st;
    for (auto c: s) {
        switch (c) {
            case ')':
                if (st.empty()) return false;
                if (st.top() != '(') return false;
                st.pop();
                break;
            case ']':
                if (st.empty()) return false;
                if (st.top() != '[') return false;
                st.pop();
                break;
            case '}':
                if (st.empty()) return false;
                if (st.top() != '{') return false;
                st.pop();
                break;
            default:
                st.push(c);
                break;
        }
    }

    return st.empty();
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"s"};
    return GenericTestMain(args, "is_valid_parenthesization.cc",
                           "is_valid_parenthesization.tsv", &IsWellFormed,
                           DefaultComparator{}, param_names);
}
