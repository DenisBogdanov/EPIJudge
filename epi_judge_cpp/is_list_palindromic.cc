#include "list_node.h"
#include "test_framework/generic_test.h"

int count = 0;

bool IsLinkedListAPalindrome(shared_ptr<ListNode<int>> list) {
    count++;
    if (list == nullptr || list->next == nullptr) return true;

    int length = 0;
    auto runner = list;

    while (runner != nullptr) {
        length++;
        runner = runner->next;
    }

    shared_ptr<ListNode<int>> reversedDummy(new ListNode<int>);
    int reverseStartIndex = (length + 1) / 2;
    int half = length / 2;

    runner = list;
    for (int i = 0; i < reverseStartIndex; ++i) {
        runner = runner->next;
    }

    reversedDummy->next = runner;
    while (runner->next != nullptr) {
        auto temp = runner->next;
        runner->next = runner->next->next;
        temp->next = reversedDummy->next;
        reversedDummy->next = temp;
    }

    runner = list;
    auto reversedRunner = reversedDummy->next;

    for (int i = 0; i < half; ++i) {
        if (runner->data != reversedRunner->data) return false;
        runner = runner->next;
        reversedRunner = reversedRunner->next;
    }

    return true;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"L"};
    return GenericTestMain(args, "is_list_palindromic.cc",
                           "is_list_palindromic.tsv", &IsLinkedListAPalindrome,
                           DefaultComparator{}, param_names);
}
