#include <memory>

#include "list_node.h"
#include "test_framework/generic_test.h"

using std::shared_ptr;

// Assumes L has at least k nodes, deletes the k-th last node in L.
shared_ptr<ListNode<int>> RemoveKthLast(const shared_ptr<ListNode<int>> &head, int k) {
    auto first = head;
    for (int i = 0; i < k; ++i) {
        first = first->next;
    }

    if (!first) {
        return head->next;
    }

    first = first->next;

    auto second = head;
    while (first) {
        first = first->next;
        second = second->next;
    }

    second->next = second->next->next;

    return head;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"L", "k"};
    return GenericTestMain(args, "delete_kth_last_from_list.cc",
                           "delete_kth_last_from_list.tsv", &RemoveKthLast,
                           DefaultComparator{}, param_names);
}
