#include <string>
#include <unordered_map>

#include "test_framework/generic_test.h"

using namespace std;

int Evaluate(const string &expression) {
    stack<int> st;
    stringstream ss(expression);
    string token;
    const char delimiter = ',';
    const unordered_map<string, function<int(int, int)>> operatorToFuncMap = {
            {"+", [](int x, int y) { return x + y; }},
            {"-", [](int x, int y) { return x - y; }},
            {"*", [](int x, int y) { return x * y; }},
            {"/", [](int x, int y) { return x / y; }},
    };

    while (getline(ss, token, delimiter)) {
        if (operatorToFuncMap.count(token)) {
            const int y = st.top();
            st.pop();
            const int x = st.top();
            st.pop();
            st.emplace(operatorToFuncMap.at(token)(x, y));
        } else {
            st.emplace(stoi(token));
        }
    }

    return st.top();
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"expression"};
    return GenericTestMain(args, "evaluate_rpn.cc", "evaluate_rpn.tsv", &Evaluate,
                           DefaultComparator{}, param_names);
}
