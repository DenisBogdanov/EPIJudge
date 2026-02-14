#include <string>

#include "test_framework/generic_test.h"
#include "test_framework/timed_executor.h"

using std::string;

void ReverseWords(string &s) {
    std::reverse(s.begin(), s.end());
    int start = 0;
    while (start < s.size() && s[start] == ' ') start++;
    if (start == s.size()) return;
    int end = s.size() - 1;
    while (s[end] == ' ') end--;

    for (int i = start; i <= end; ++i) {
        if (s[i] == ' ' || i == end) {
            std::reverse(s.begin() + start, s.begin() + i + (i == end));
            start = i + 1;
        }
    }
}

string ReverseWordsWrapper(TimedExecutor &executor, string s) {
    string s_copy = s;

    executor.Run([&] { ReverseWords(s_copy); });

    return s_copy;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "s"};
    return GenericTestMain(args, "reverse_words.cc", "reverse_words.tsv",
                           &ReverseWordsWrapper, DefaultComparator{},
                           param_names);
}
