#include <string>

#include "test_framework/generic_test.h"
#include "test_framework/timed_executor.h"

using std::string;

void ReverseWords(string *s) {
    string &str = *s;

    std::reverse(str.begin(), str.end());

    int prev = -1;
    for (int i = 0; i < str.length(); i++) {
        if ((*s)[i] == ' ') {
            std::reverse(str.begin() + prev + 1, str.begin() + i);
            prev = i;
        }

        if (i == str.length() - 1) {
            std::reverse(str.begin() + prev + 1, str.end());
        }
    }
}

string ReverseWordsWrapper(TimedExecutor &executor, string s) {
    string s_copy = s;

    executor.Run([&] { ReverseWords(&s_copy); });

    return s_copy;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "s"};
    return GenericTestMain(args, "reverse_words.cc", "reverse_words.tsv",
                           &ReverseWordsWrapper, DefaultComparator{},
                           param_names);
}
