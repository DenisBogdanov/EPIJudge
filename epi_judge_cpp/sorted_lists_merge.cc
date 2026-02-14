#include "list_node.h"
#include "test_framework/generic_test.h"

using namespace std;

shared_ptr<ListNode<int>> MergeTwoSortedLists(shared_ptr<ListNode<int>> list1,
                                              shared_ptr<ListNode<int>> list2) {

    auto dummy = make_shared<ListNode<int>>(-1);
    auto runner = dummy;
    while (list1 != nullptr || list2 != nullptr) {
        if (list2 == nullptr || (list1 != nullptr && list1->data < list2->data)) {
            runner->next = make_shared<ListNode<int>>(list1->data);
            list1 = list1->next;
        } else {
            runner->next = make_shared<ListNode<int>>(list2->data);
            list2 = list2->next;
        }
        runner = runner->next;
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
