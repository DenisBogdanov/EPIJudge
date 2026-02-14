#include <string>
#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

vector<vector<string>> FindAnagrams(const vector<string> &dictionary) {
    map<string, vector<string>> sorted_string_to_strings_map;
    for (const auto &s: dictionary) {
        string copy = s;
        std::sort(copy.begin(), copy.end());
        sorted_string_to_strings_map[copy].push_back(s);
    }
    vector<vector<string>> ans;
    for (auto &[_, strings]: sorted_string_to_strings_map) {
        if (strings.size() == 1) continue;
        ans.push_back(strings);
    }
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"dictionary"};
    return GenericTestMain(args, "anagrams.cc", "anagrams.tsv", &FindAnagrams,
                           UnorderedComparator{}, param_names);
}
