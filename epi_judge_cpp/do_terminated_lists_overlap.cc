#include <memory>

#include "list_node.h"
#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"
#include "test_framework/timed_executor.h"

using std::shared_ptr;

shared_ptr<ListNode<int>> OverlappingNoCycleLists(shared_ptr<ListNode<int>> &list1, shared_ptr<ListNode<int>> &list2) {
    if (list1 == nullptr || list2 == nullptr) return nullptr;
    auto r1 = list1;
    auto r2 = list2;
    while ((r1 != nullptr || r2 != nullptr))  {
        if (r1 == nullptr) {
            r1 = list2;
        }
        if (r2 == nullptr) {
            r2 = list1;
        }
        r1 = r1->next;
        r2 = r2->next;
        if (r1 == r2) return r1;
    }
    return nullptr;
}

void OverlappingNoCycleListsWrapper(TimedExecutor &executor,
                                    shared_ptr<ListNode<int>> l0,
                                    shared_ptr<ListNode<int>> l1,
                                    shared_ptr<ListNode<int>> common) {
    if (common) {
        if (l0) {
            auto i = l0;
            while (i->next) {
                i = i->next;
            }
            i->next = common;
        } else {
            l0 = common;
        }

        if (l1) {
            auto i = l1;
            while (i->next) {
                i = i->next;
            }
            i->next = common;
        } else {
            l1 = common;
        }
    }

    auto result = executor.Run([&] { return OverlappingNoCycleLists(l0, l1); });

    if (result != common) {
        throw TestFailure("Invalid result");
    }
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "l0", "l1", "common"};
    return GenericTestMain(
            args, "do_terminated_lists_overlap.cc", "do_terminated_lists_overlap.tsv",
            &OverlappingNoCycleListsWrapper, DefaultComparator{}, param_names);
}
