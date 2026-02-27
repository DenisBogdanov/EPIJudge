#include <string>
#include <unordered_set>
#include <vector>

#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"
#include "test_framework/timed_executor.h"
using namespace std;

vector<string> DecomposeIntoDictionaryWords(
    const string &domain, const unordered_set<string> &dictionary) {
    vector<bool> dp(domain.size() + 1, false);
    vector<string> path(domain.size() + 1);
    dp[0] = true;
    path[0] = "";
    for (int end = 1; end <= domain.size(); end++) {
        for (const string &word: dictionary) {
            auto word_size = word.size();
            if (end >= word_size
                && dp[end - word_size]
                && domain.substr(end - word_size, word_size) == word) {

                path[end] = word;
                dp[end] = true;
            }
        }
    }

    vector<string> ans;
    if (dp[domain.size()]) {
        int idx = domain.size();
        while (idx > 0) {
            ans.push_back(path[idx]);
            idx -= path[idx].size();
        }
        reverse(ans.begin(), ans.end());
    }
    return ans;
}

void DecomposeIntoDictionaryWordsWrapper(
    TimedExecutor &executor, const string &domain,
    const unordered_set<string> &dictionary, bool decomposable) {
    vector<string> result = executor.Run(
        [&] { return DecomposeIntoDictionaryWords(domain, dictionary); });
    if (!decomposable) {
        if (!result.empty()) {
            throw TestFailure("domain is not decomposable");
        }
        return;
    }

    if (std::any_of(std::begin(result), std::end(result),
                    [&](const std::string &s) { return !dictionary.count(s); })) {
        throw TestFailure("Result uses words not in dictionary");
    }

    if (std::accumulate(std::begin(result), std::end(result), string()) !=
        domain) {
        throw TestFailure("Result is not composed into domain");
    }
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{
        "executor", "domain", "dictionary",
        "decomposable"
    };
    return GenericTestMain(args, "is_string_decomposable_into_words.cc",
                           "is_string_decomposable_into_words.tsv",
                           &DecomposeIntoDictionaryWordsWrapper,
                           DefaultComparator{}, param_names);
}
