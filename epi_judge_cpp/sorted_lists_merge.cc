#include "list_node.h"
#include "test_framework/generic_test.h"

shared_ptr<ListNode<int>> MergeTwoSortedLists(shared_ptr<ListNode<int>> head1, shared_ptr<ListNode<int>> head2) {
    auto dummy = std::make_shared<ListNode<int>>(-1);
    auto r1 = head1;
    auto r2 = head2;
    auto r = dummy;

    while (r1 || r2) {
        if (!r2) {
            r->next = r1;
            r = r->next;
            r1 = r1->next;
        } else if (!r1) {
            r->next = r2;
            r = r->next;
            r2 = r2->next;
        } else {
            if (r1->data < r2->data) {
                r->next = r1;
                r = r->next;
                r1 = r1->next;
            } else {
                r->next = r2;
                r = r->next;
                r2 = r2->next;
            }
        }
    }

    return dummy->next;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"L1", "L2"};
    return GenericTestMain(args, "sorted_lists_merge.cc",
                           "sorted_lists_merge.tsv", &MergeTwoSortedLists,
                           DefaultComparator{}, param_names);
}
