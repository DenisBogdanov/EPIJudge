#include <memory>

#include "list_node.h"
#include "test_framework/generic_test.h"

using std::shared_ptr;

shared_ptr<ListNode<int>> CyclicallyRightShiftList(shared_ptr<ListNode<int>> list, int k) {
    if (k == 0 || list == nullptr) return list;

    auto runner = list;
    int length = 0;

    shared_ptr<ListNode<int>> lastNode = nullptr;
    while (runner != nullptr) {
        length++;
        if (runner->next == nullptr) {
            lastNode = runner;
        }

        runner = runner->next;
    }

    k %= length;
    if (k == 0) return list;

    auto beforeNewHead = list;
    for (int i = 0; i < length - k - 1; i++) {
        beforeNewHead = beforeNewHead->next;
    }

    auto newHead = beforeNewHead->next;
    beforeNewHead->next = nullptr;
    lastNode->next = list;

    return newHead;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"L", "k"};
    return GenericTestMain(
            args, "list_cyclic_right_shift.cc", "list_cyclic_right_shift.tsv",
            &CyclicallyRightShiftList, DefaultComparator{}, param_names);
}
