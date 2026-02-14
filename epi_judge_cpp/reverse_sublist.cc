#include "list_node.h"
#include "test_framework/generic_test.h"

shared_ptr<ListNode<int>> ReverseSublist(shared_ptr<ListNode<int>> list, int start, int finish) {
    if (list == nullptr) return list;
    if (start == finish) return list;
    auto dummy = make_shared<ListNode<int>>(-1);
    dummy->next = list;
    auto prev = dummy;
    for (int i = 1; i < start; ++i) {
        prev = prev->next;
    }

    auto runner = prev->next;
    for (int i = start; i < finish; ++i) {
        auto temp = prev->next;
        prev->next = runner->next;
        runner->next = runner->next->next;
        prev->next->next = temp;
    }

    return dummy->next;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"L", "start", "finish"};
    return GenericTestMain(args, "reverse_sublist.cc", "reverse_sublist.tsv",
                           &ReverseSublist, DefaultComparator{}, param_names);
}
