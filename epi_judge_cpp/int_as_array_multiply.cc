#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<int> MultiplyByNum(vector<int> num1, int num2);

vector<int> AddTo(vector<int> num1, vector<int> num2, int shift);

vector<int> Multiply(vector<int> num1, vector<int> num2) {
    int sign = num1[0] * num2[0] >= 0 ? 1 : -1;
    num1[0] = abs(num1[0]);
    num2[0] = abs(num2[0]);

    vector<int> result;
    result.push_back(0);

    for (int i = num2.size() - 1; i >= 0; --i) {
        vector<int> next = MultiplyByNum(num1, num2[i]);
        result = AddTo(result, next, num2.size() - i - 1);
    }

    result[0] *= sign;
    return result;
}

vector<int> AddTo(vector<int> num1, vector<int> num2, int shift) {
    vector<int> result;
    if (num2.size() == 1 && num2[0] == 0) {
        return num1;
    }

    for (int i = 0; i < shift; ++i) {
        num2.push_back(0);
    }

    int carry = 0;
    int index = 0;
    while (index < num1.size() || index < num2.size()) {
        int next = carry;
        if (index < num1.size()) {
            next += num1[num1.size() - 1 - index];
        }
        if (index < num2.size()) {
            next += num2[num2.size() - 1 - index];
        }

        carry = next / 10;
        result.push_back(next % 10);
        index++;
    }


    if (carry > 0) {
        result.push_back(carry);
    }

    std::reverse(result.begin(), result.end());
    return result;
}

vector<int> MultiplyByNum(vector<int> num1, int num2) {
    vector<int> result;
    if (num2 == 0) {
        result.push_back(0);
        return result;
    }

    int carry = 0;

    for (int i = num1.size() - 1; i >= 0; --i) {
        int next = num1[i] * num2 + carry;
        carry = next / 10;

        result.push_back(next % 10);
    }

    if (carry > 0) {
        result.push_back(carry);
    }

    std::reverse(result.begin(), result.end());
    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"num1", "num2"};
    return GenericTestMain(args, "int_as_array_multiply.cc",
                           "int_as_array_multiply.tsv", &Multiply,
                           DefaultComparator{}, param_names);
}
