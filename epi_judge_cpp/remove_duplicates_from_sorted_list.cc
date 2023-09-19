#include <memory>

#include "list_node.h"
#include "test_framework/generic_test.h"

using std::shared_ptr;

shared_ptr<ListNode<int>> RemoveDuplicates(const shared_ptr<ListNode<int>> &list) {
    if (list == nullptr) return list;

    auto runner = list;
    while (runner != nullptr && runner->next != nullptr) {
        if (runner->data != runner->next->data) {
            runner = runner->next;
        } else {
            runner->next = runner->next->next;
        }
    }

    return list;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"L"};
    return GenericTestMain(args, "remove_duplicates_from_sorted_list.cc",
                           "remove_duplicates_from_sorted_list.tsv",
                           &RemoveDuplicates, DefaultComparator{}, param_names);
}
