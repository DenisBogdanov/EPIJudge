#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

vector<int> MergeSortedArrays(const vector<vector<int>> &sorted_arrays) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, function<bool(pair<int, int>, pair<int, int>)>> min_heap(
            [](const pair<int, int> &a, const pair<int, int> &b) { return a.first >= b.first; });

    for (int i = 0; i < sorted_arrays.size(); ++i) {
        min_heap.push(pair<int, int>(sorted_arrays[i][0], i));
    }

    vector<int> arr_indexes(sorted_arrays.size(), 0);

    vector<int> result;
    while (!min_heap.empty()) {
        result.push_back(min_heap.top().first);
        int arr_index = min_heap.top().second;
        min_heap.pop();

        arr_indexes[arr_index]++;
        if (arr_indexes[arr_index] < sorted_arrays[arr_index].size()) {
            min_heap.push(pair<int, int>(sorted_arrays[arr_index][arr_indexes[arr_index]], arr_index));
        }
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"sorted_arrays"};
    return GenericTestMain(args, "sorted_arrays_merge.cc",
                           "sorted_arrays_merge.tsv", &MergeSortedArrays,
                           DefaultComparator{}, param_names);
}
