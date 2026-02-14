#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

vector<int> MergeSortedArrays(const vector<vector<int>> &sorted_arrays) {
    vector<int> ans;
    // value, array idx, idx inside array
    priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<>> min_heap;
    for (int i = 0; i < sorted_arrays.size(); i++) {
        if (sorted_arrays[i].empty()) continue;
        min_heap.emplace(sorted_arrays[i][0], i, 0);
    }
    while (!min_heap.empty()) {
        auto [value, arr_idx, idx] = min_heap.top();
        min_heap.pop();
        ans.push_back(value);
        if (idx + 1 < sorted_arrays[arr_idx].size()) {
            min_heap.emplace(sorted_arrays[arr_idx][idx + 1], arr_idx, idx + 1);
        }
    }
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"sorted_arrays"};
    return GenericTestMain(args, "sorted_arrays_merge.cc",
                           "sorted_arrays_merge.tsv", &MergeSortedArrays,
                           DefaultComparator{}, param_names);
}
