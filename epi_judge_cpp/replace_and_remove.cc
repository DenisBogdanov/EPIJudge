#include <iterator>
#include <string>
#include <vector>

#include "test_framework/generic_test.h"
#include "test_framework/timed_executor.h"

using std::string;
using std::vector;

int ReplaceAndRemove(int size, char s[]) {
    int writeIndex = 0;
    int aCount = 0;
    for (int i = 0; i < size; i++) {
        if (s[i] != 'b') {
            s[writeIndex++] = s[i];
            if (s[i] == 'a') aCount++;
        }
    }

    int currIndex = writeIndex - 1;
    writeIndex += aCount - 1;
    int totalSize = writeIndex + 1;

    while (currIndex >= 0) {
        if (s[currIndex] == 'a') {
            s[writeIndex--] = 'd';
            s[writeIndex--] = 'd';
        } else {
            s[writeIndex--] = s[currIndex];
        }

        currIndex--;
    }

    return totalSize;
}

vector<string> ReplaceAndRemoveWrapper(TimedExecutor &executor, int size,
                                       const vector<string> &s) {
    std::vector<char> s_copy(s.size(), '\0');
    for (int i = 0; i < s.size(); ++i) {
        if (!s[i].empty()) {
            s_copy[i] = s[i][0];
        }
    }

    int res_size =
            executor.Run([&] { return ReplaceAndRemove(size, s_copy.data()); });

    vector<string> result;
    for (int i = 0; i < res_size; ++i) {
        result.emplace_back(string(1, s_copy[i]));
    }
    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "size", "s"};
    return GenericTestMain(args, "replace_and_remove.cc",
                           "replace_and_remove.tsv", &ReplaceAndRemoveWrapper,
                           DefaultComparator{}, param_names);
}
