#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

vector<int> SortApproximatelySortedData(
        vector<int>::const_iterator sequence_begin,
        const vector<int>::const_iterator &sequence_end, int k) {

    priority_queue<int, vector<int>, greater<>> minHeap;

    for (int i = 0; i < k && sequence_begin != sequence_end; ++i) {
        minHeap.push(*sequence_begin++);
    }

    vector<int> result;
    while (sequence_begin != sequence_end) {
        minHeap.push(*sequence_begin++);
        result.push_back(minHeap.top());
        minHeap.pop();
    }

    while (!minHeap.empty()) {
        result.push_back(minHeap.top());
        minHeap.pop();
    }

    return result;
}

vector<int> SortApproximatelySortedDataWrapper(const vector<int> &sequence, int k) {
    return SortApproximatelySortedData(cbegin(sequence), cend(sequence), k);
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"sequence", "k"};
    return GenericTestMain(
            args, "sort_almost_sorted_array.cc", "sort_almost_sorted_array.tsv",
            &SortApproximatelySortedDataWrapper, DefaultComparator{}, param_names);
}
