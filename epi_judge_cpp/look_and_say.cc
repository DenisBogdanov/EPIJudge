#include <string>

#include "test_framework/generic_test.h"
using namespace std;

string LookAndSay(int n) {
    if (n == 0) return "";
    string ans = "1";
    string temp;
    for (int i = 1; i < n; i++) {
        int count = 1;
        for (int j = 1; j < ans.size(); j++) {
            if (ans[j] == ans[j - 1]) {
                count++;
            } else {
                temp += to_string(count) + ans[j - 1];
                count = 1;
            }
        }
        temp += to_string(count) + ans.back();
        swap(ans, temp);
        temp.clear();
    }
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"n"};
    return GenericTestMain(args, "look_and_say.cc", "look_and_say.tsv",
                           &LookAndSay, DefaultComparator{}, param_names);
}
