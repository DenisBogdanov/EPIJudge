#include <iterator>
#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

vector<int> ExamineBuildingsWithSunset(
        vector<int>::const_iterator sequence_begin,
        const vector<int>::const_iterator &sequence_end) {

    struct BuildingWithHeight {
        int id;
        int height;
    };

    stack<BuildingWithHeight> st;
    int index = 0;
    while (sequence_begin != sequence_end) {
        int nextHeight = *sequence_begin++;
        while (!st.empty() && st.top().height <= nextHeight) {
            st.pop();
        }

        st.emplace(BuildingWithHeight{index++, nextHeight});
    }

    vector<int> result;
    while (!st.empty()) {
        result.emplace_back(st.top().id);
        st.pop();
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
