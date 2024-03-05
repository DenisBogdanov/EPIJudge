#include <string>

#include "test_framework/generic_test.h"

using namespace std;

bool IsWellFormed(const string &s) {
    stack<char> st;

    for (char token: s) {
        switch (token) {
            case ')':
                if (st.empty() || st.top() != '(') return false;
                st.pop();
                break;
            case ']':
                if (st.empty() || st.top() != '[') return false;
                st.pop();
                break;
            case '}':
                if (st.empty() || st.top() != '{') return false;
                st.pop();
                break;
            default:
                st.push(token);
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
