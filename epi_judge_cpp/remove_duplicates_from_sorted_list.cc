#include <memory>

#include "list_node.h"
#include "test_framework/generic_test.h"

using std::shared_ptr;

shared_ptr<ListNode<int>> RemoveDuplicates(const shared_ptr<ListNode<int>> &head) {
    if (!head) return head;

    auto read = head->next;
    auto write = head;

    while (read) {
        if (read->data != write->data) {
            write->next = read;
            write = write->next;
        }

        read = read->next;
    }

    write->next = nullptr;

    return head;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"L"};
    return GenericTestMain(args, "remove_duplicates_from_sorted_list.cc",
                           "remove_duplicates_from_sorted_list.tsv",
                           &RemoveDuplicates, DefaultComparator{}, param_names);
}
