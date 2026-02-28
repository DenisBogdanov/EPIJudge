#include <vector>

#include "test_framework/generic_test.h"
using namespace std;

int FindElementAppearsOnce(const vector<int> &nums) {
    unordered_map<int, int> num_to_count_map;
    for (int num : nums) {
        num_to_count_map[num]++;
    }
    for (auto &[num, count] : num_to_count_map) {
        if (count == 1) return num;
    }
    return 0;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A"};
    return GenericTestMain(args, "element_appearing_once.cc",
                           "element_appearing_once.tsv", &FindElementAppearsOnce,
                           DefaultComparator{}, param_names);
}
