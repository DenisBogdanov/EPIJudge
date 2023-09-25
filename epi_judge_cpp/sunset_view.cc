#include <iterator>
#include <vector>

#include "test_framework/generic_test.h"

using std::vector;
using std::stack;

vector<int> ExamineBuildingsWithSunset(
        vector<int>::const_iterator sequence_begin,
        const vector<int>::const_iterator &sequence_end) {

    struct Building {
        int id;
        int height;
    };

    stack<Building> order;
    int index = 0;

    while (sequence_begin != sequence_end) {
        int building_height = *sequence_begin++;
        while (!order.empty() && building_height >= order.top().height) {
            order.pop();
        }

        order.emplace(Building{index++, building_height});
    }

    vector<int> result;
    while (!order.empty()) {
        result.emplace_back(order.top().id);
        order.pop();
    }

    return result;
}

vector<int> ExamineBuildingsWithSunsetWrapper(const vector<int> &sequence) {
    return ExamineBuildingsWithSunset(cbegin(sequence), cend(sequence));
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"sequence"};
    return GenericTestMain(args, "sunset_view.cc", "sunset_view.tsv",
                           &ExamineBuildingsWithSunsetWrapper,
                           DefaultComparator{}, param_names);
}
