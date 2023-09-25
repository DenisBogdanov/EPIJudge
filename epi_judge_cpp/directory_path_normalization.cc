#include <string>

#include "test_framework/generic_test.h"

using namespace std;

string ShortestEquivalentPath(const string &path) {
    vector<string> v;

    if (path.front() == '/') v.emplace_back("/");

    stringstream ss(path);
    string token;

    while (std::getline(ss, token, '/')) {
        if (token == "..") {
            if (v.empty() || v.back() == "..") {
                v.emplace_back("..");
            } else {
                if (v.back() == "/") {
                    throw invalid_argument("Path error");
                }

                v.pop_back();
            }
        } else if (token != "." && !token.empty()) {
            v.push_back(token);
        }
    }

    string result;
    if (!v.empty()) {
        result = v.front();
        for (int i = 1; i < v.size(); ++i) {
            if (i == 1 && result == "/") {
                result += v[i];
            } else {
                result += "/" + v[i];
            }
        }
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"path"};
    return GenericTestMain(args, "directory_path_normalization.cc",
                           "directory_path_normalization.tsv",
                           &ShortestEquivalentPath, DefaultComparator{},
                           param_names);
}
