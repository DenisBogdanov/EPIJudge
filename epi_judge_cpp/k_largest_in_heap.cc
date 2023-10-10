#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

vector<int> KLargestInBinaryHeap(const vector<int> &A, int k) {
    vector<int> result;
    if (k == 0) return result;
    int a = 0;
    int b = 1;

    struct HeapEntry {
        int index;
        int value;
    };

    priority_queue<HeapEntry, vector<HeapEntry>, function<bool(HeapEntry, HeapEntry)>> maxHeap(
            [](const HeapEntry &a, const HeapEntry &b) { return a.value < b.value; });

    maxHeap.push(HeapEntry{0, A[0]});

    for (int i = 0; i < k; ++i) {
        HeapEntry popped = maxHeap.top();
        maxHeap.pop();

        result.push_back(popped.value);

        int leftIndex = popped.index * 2 + 1;
        int rightIndex = popped.index * 2 + 2;

        if (leftIndex < A.size()) {
            maxHeap.push(HeapEntry{leftIndex, A[leftIndex]});
        }

        if (rightIndex < A.size()) {
            maxHeap.push(HeapEntry{rightIndex, A[rightIndex]});
        }
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A", "k"};
    return GenericTestMain(args, "k_largest_in_heap.cc", "k_largest_in_heap.tsv",
                           &KLargestInBinaryHeap, UnorderedComparator{},
                           param_names);
}
