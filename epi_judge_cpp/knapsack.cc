#include <vector>

#include "test_framework/generic_test.h"
#include "test_framework/serialization_traits.h"
using namespace std;

struct Item {
    int weight, value;
};

int OptimumSubjectToCapacity(const vector<Item> &items, int capacity) {
    vector<int> dp(capacity + 1, 0);
    for (auto &item : items) {
        for (int i = capacity; i >= item.weight; i--) {
            dp[i] = max(dp[i], dp[i - item.weight] + item.value);
        }
    }
    return *max_element(dp.begin(), dp.end());
}

namespace test_framework {
    template<>
    struct SerializationTrait<Item> : UserSerTrait<Item, int, int> {
    };
} // namespace test_framework

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"items", "capacity"};
    return GenericTestMain(args, "knapsack.cc", "knapsack.tsv",
                           &OptimumSubjectToCapacity, DefaultComparator{},
                           param_names);
}
