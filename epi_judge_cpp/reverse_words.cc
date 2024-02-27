#include <string>

#include "test_framework/generic_test.h"
#include "test_framework/timed_executor.h"

using namespace std;

void ReverseWords(string *s) {
    string &str = *s;
    reverse(str.begin(), str.end());

    auto start = str.begin();
    for (auto end = str.begin(); end != str.end(); ++end) {
        if (*end == ' ') {
            reverse(start, end);
            start = end + 1;
        }
    }

    reverse(start, str.end());
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
