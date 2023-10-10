#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

vector<double> OnlineMedian(vector<int>::const_iterator sequence_begin,
                            const vector<int>::const_iterator &sequence_end) {

    priority_queue<int, vector<int>, greater<>> minHeap;
    priority_queue<int> maxHeap;

    vector<double> result;

    while (sequence_begin != sequence_end) {
        int newNum = *sequence_begin++;

        if (minHeap.empty()) {
            minHeap.push(newNum);
        } else {
            if (newNum >= minHeap.top()) {
                minHeap.push(newNum);
            } else {
                maxHeap.push(newNum);
            }
        }

        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.push(minHeap.top());
            minHeap.pop();
        } else if (maxHeap.size() > minHeap.size()) {
            minHeap.push(maxHeap.top());
            maxHeap.pop();
        }

        if (minHeap.size() == maxHeap.size()) result.push_back((minHeap.top() + maxHeap.top()) / 2.0);
        else result.push_back(minHeap.top());
    }

    return result;
}

vector<double> OnlineMedianWrapper(const vector<int> &sequence) {
    return OnlineMedian(cbegin(sequence), cend(sequence));
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"sequence"};
    return GenericTestMain(args, "online_median.cc", "online_median.tsv",
                           &OnlineMedianWrapper, DefaultComparator{},
                           param_names);
}
