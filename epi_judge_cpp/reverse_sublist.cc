#include "list_node.h"
#include "test_framework/generic_test.h"

shared_ptr<ListNode<int>> ReverseSublist(shared_ptr<ListNode<int>> L, int start, int finish) {
    if (start == finish) return L;

    auto dummy = make_shared<ListNode<int>>(ListNode<int>{-1, L});

    auto beforeStartNode = dummy;
    int k = 1;
    while (k++ < start) {
        beforeStartNode = beforeStartNode->next;
    }

    auto runner = beforeStartNode->next;

    while (start++ < finish) {
        auto temp = runner->next;
        runner->next = temp->next;
        temp->next = beforeStartNode->next;
        beforeStartNode->next = temp;
    }

    return dummy->next;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"L", "start", "finish"};
    return GenericTestMain(args, "reverse_sublist.cc", "reverse_sublist.tsv",
                           &ReverseSublist, DefaultComparator{}, param_names);
}
