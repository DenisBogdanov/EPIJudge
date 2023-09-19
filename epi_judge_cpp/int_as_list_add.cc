#include <memory>

#include "list_node.h"
#include "test_framework/generic_test.h"

shared_ptr<ListNode<int>> AddTwoNumbers(shared_ptr<ListNode<int>> list1, shared_ptr<ListNode<int>> list2) {
    shared_ptr<ListNode<int>> dummy(new ListNode<int>);
    int carry = 0;

    auto r1 = list1;
    auto r2 = list2;

    auto runner = dummy;

    while (r1 != nullptr || r2 != nullptr) {
        int result = carry;
        if (r1 != nullptr) {
            result += r1->data;
            r1 = r1->next;
        }

        if (r2 != nullptr) {
            result += r2->data;
            r2 = r2->next;
        }

        carry = result / 10;

        runner->next = std::make_shared<ListNode<int>>(result % 10);
        runner = runner->next;
    }

    if (carry == 1) runner->next = std::make_shared<ListNode<int>>(1);

    return dummy->next;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"L1", "L2"};
    return GenericTestMain(args, "int_as_list_add.cc", "int_as_list_add.tsv",
                           &AddTwoNumbers, DefaultComparator{}, param_names);
}
