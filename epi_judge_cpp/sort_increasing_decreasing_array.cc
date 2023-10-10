#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

vector<int> MergeSortedArrays(const vector<vector<int>> &sorted_arrays);

vector<int> SortKIncreasingDecreasingArray(const vector<int> &arr) {
    vector<vector<int>> sortedArrays;

    int prevIndex = 0;
    bool isIncreasing = true;
    auto n = arr.size();

    for (int i = 0; i < n - 1; ++i) {
        if (isIncreasing) {
            if (arr[i] > arr[i + 1]) {
                sortedArrays.emplace_back(arr.cbegin() + prevIndex, arr.cbegin() + i + 1);
                isIncreasing = false;
                prevIndex = i + 1;
            }
        } else {
            if (arr[i] < arr[i + 1]) {
                sortedArrays.emplace_back(arr.crbegin() + n - (i + 1), arr.crbegin() + n - prevIndex);
                isIncreasing = true;
                prevIndex = i + 1;
            }
        }
    }

    if (isIncreasing) {
        sortedArrays.emplace_back(arr.cbegin() + prevIndex, arr.end());
    } else {
        sortedArrays.emplace_back(arr.crbegin(), arr.crbegin() + n - prevIndex);
    }

    return MergeSortedArrays(sortedArrays);
}

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
    std::vector<std::string> param_names{"A"};
    return GenericTestMain(args, "sort_increasing_decreasing_array.cc",
                           "sort_increasing_decreasing_array.tsv",
                           &SortKIncreasingDecreasingArray, DefaultComparator{},
                           param_names);
}
