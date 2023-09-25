#include <string>

#include "test_framework/generic_test.h"

using namespace std;

int Evaluate(const string &expression) {
    stack<int> results;
    stringstream ss(expression);
    string token;
    const char delimiter = ',';

    while (getline(ss, token, delimiter)) {
        if (token == "+" || token == "-" || token == "*" || token == "/") {
            const int b = results.top();
            results.pop();
            const int a = results.top();
            results.pop();

            switch (token.front()) {
                case '+':
                    results.push(a + b);
                    break;
                case '-':
                    results.push(a - b);
                    break;
                case '*':
                    results.push(a * b);
                    break;
                case '/':
                    results.push(a / b);
                    break;
            }
        } else {
            results.push(stoi(token));
        }
    }

    return results.top();
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"expression"};
    return GenericTestMain(args, "evaluate_rpn.cc", "evaluate_rpn.tsv", &Evaluate,
                           DefaultComparator{}, param_names);
}
