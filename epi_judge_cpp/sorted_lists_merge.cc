#include "list_node.h"
#include "test_framework/generic_test.h"

shared_ptr<ListNode<int>> MergeTwoSortedLists(shared_ptr<ListNode<int>> L1,
                                              shared_ptr<ListNode<int>> L2) {

    shared_ptr<ListNode<int>> dummy(new ListNode<int>);
    auto runner = dummy;

    while (L1 && L2) {
        if (L1->data < L2->data) {
            runner->next = L1;
            L1 = L1->next;
        } else {
            runner->next = L2;
            L2 = L2->next;
        }

        runner = runner->next;
    }

    runner->next = L1 ? L1 : L2;

    return dummy->next;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"L1", "L2"};
    return GenericTestMain(args, "sorted_lists_merge.cc",
                           "sorted_lists_merge.tsv", &MergeTwoSortedLists,
                           DefaultComparator{}, param_names);
}
