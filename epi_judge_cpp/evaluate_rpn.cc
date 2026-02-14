#include <string>

#include "test_framework/generic_test.h"

using namespace std;

int Evaluate(const string &expression) {
    stringstream ss(expression);
    string token;
    deque<int> stack;
    while (getline(ss, token, ',')) {
        if (token == "*") {
            int a = stack.front();
            stack.pop_front();
            int b = stack.front();
            stack.pop_front();
            stack.push_front(a * b);
        } else if (token == "+") {
            int a = stack.front();
            stack.pop_front();
            int b = stack.front();
            stack.pop_front();
            stack.push_front(a + b);
        } else if (token == "-") {
            int a = stack.front();
            stack.pop_front();
            int b = stack.front();
            stack.pop_front();
            stack.push_front(b - a);
        } else if (token == "/") {
            int a = stack.front();
            stack.pop_front();
            int b = stack.front();
            stack.pop_front();
            stack.push_front(b / a);
        } else {
            stack.push_front(stoi(token));
        }
    }
    return stack.front();
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"expression"};
    return GenericTestMain(args, "evaluate_rpn.cc", "evaluate_rpn.tsv", &Evaluate,
                           DefaultComparator{}, param_names);
}
