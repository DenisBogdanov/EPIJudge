#include "list_node.h"
#include "test_framework/generic_test.h"

shared_ptr<ListNode<int>> EvenOddMerge(const shared_ptr<ListNode<int>> &list) {
    shared_ptr<ListNode<int>> evenDummy(new ListNode<int>);
    shared_ptr<ListNode<int>> oddDummy(new ListNode<int>);

    auto evenRunner = evenDummy;
    auto oddRunner = oddDummy;

    auto runner = list;

    int i = 0;
    while (runner != nullptr) {
        if (i % 2 == 0) {
            evenRunner->next = runner;
            evenRunner = evenRunner->next;
        } else {
            oddRunner->next = runner;
            oddRunner = oddRunner->next;
        }

        runner = runner->next;
        i++;
    }

    evenRunner->next = oddDummy->next;
    oddRunner->next = nullptr;

    return evenDummy->next;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"L"};
    return GenericTestMain(args, "even_odd_list_merge.cc",
                           "even_odd_list_merge.tsv", &EvenOddMerge,
                           DefaultComparator{}, param_names);
}
