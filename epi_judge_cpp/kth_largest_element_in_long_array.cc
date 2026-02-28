#include <vector>

#include "test_framework/generic_test.h"
using namespace std;

int FindKthLargestUnknownLength(vector<int>::const_iterator stream_begin,
                                const vector<int>::const_iterator &stream_end,
                                int k) {

    priority_queue<int, vector<int>, greater<>> min_heap;
    auto it = stream_begin;
    while (it != stream_end) {
        min_heap.push(*it);
        if (min_heap.size() > k) {
            min_heap.pop();
        }
        ++it;
    }

    return min_heap.top();
}

int FindKthLargestUnknownLengthWrapper(const vector<int> &stream, int k) {
    return FindKthLargestUnknownLength(stream.cbegin(), stream.cend(), k);
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"stream", "k"};
    return GenericTestMain(args, "kth_largest_element_in_long_array.cc",
                           "kth_largest_element_in_long_array.tsv",
                           &FindKthLargestUnknownLengthWrapper,
                           DefaultComparator{}, param_names);
}
