#include <string>
#include <vector>

#include "test_framework/generic_test.h"

using std::string;
using std::vector;
using std::array;

const array<string, 10> kMapping = {
        {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"}};

vector<string> result;

void recur(const string& phone_number, int index, string& curr);

vector<string> PhoneMnemonic(const string& phone_number) {
    result.clear();

    string curr;
    recur(phone_number, 0, curr);

    return result;
}

void recur(const string& phone_number, int index, string& curr) {
    if (curr.size() == phone_number.size()) {
        result.emplace_back(curr);
        return;
    }

    for (auto symbol : kMapping[phone_number[index] - '0']) {
        curr += symbol;
        recur(phone_number, index + 1, curr);

        curr.pop_back();
    }
}

int main(int argc, char* argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"phone_number"};
    return GenericTestMain(args, "phone_number_mnemonic.cc",
                           "phone_number_mnemonic.tsv", &PhoneMnemonic,
                           UnorderedComparator{}, param_names);
}
