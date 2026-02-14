#include <set>
#include <stdexcept>

#include "list_node.h"
#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"
#include "test_framework/timed_executor.h"

shared_ptr<ListNode<int>> HasCycle(const shared_ptr<ListNode<int>> &head) {
    auto fast = head;
    auto slow = head;
    while (fast != nullptr && fast->next != nullptr) {
        fast = fast->next->next;
        slow = slow->next;
        if (fast == slow) {
            return fast;
        }
    }
    return nullptr;
}

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

shared_ptr<ListNode<int>> OverlappingLists(shared_ptr<ListNode<int>> &list1, shared_ptr<ListNode<int>> &list2) {
    auto cycle1 = HasCycle(list1);
    auto cycle2 = HasCycle(list2);
    if (cycle1 != nullptr && cycle2 == nullptr) return nullptr;
    if (cycle1 == nullptr && cycle2 != nullptr) return nullptr;
    if (cycle1 == nullptr && cycle2 == nullptr) {
        return OverlappingNoCycleLists(list1, list2);
    }
    auto fast = cycle1;
    auto slow = cycle2;
    while (fast != slow) {
        fast = fast->next->next;
        slow = slow->next;
        if (slow == cycle2) return nullptr;
    }
    return fast;
}

void OverlappingListsWrapper(TimedExecutor &executor,
                             shared_ptr<ListNode<int>> l0,
                             shared_ptr<ListNode<int>> l1,
                             shared_ptr<ListNode<int>> common, int cycle0,
                             int cycle1) {
    if (common) {
        if (!l0) {
            l0 = common;
        } else {
            auto it = l0;
            while (it->next) {
                it = it->next;
            }
            it->next = common;
        }

        if (!l1) {
            l1 = common;
        } else {
            auto it = l1;
            while (it->next) {
                it = it->next;
            }
            it->next = common;
        }
    }

    if (cycle0 != -1 && l0) {
        auto last = l0;
        while (last->next) {
            last = last->next;
        }
        auto it = l0;
        while (cycle0-- > 0) {
            if (!it) {
                throw std::runtime_error("Invalid input data");
            }
            it = it->next;
        }
        last->next = it;
    }

    if (cycle1 != -1 && l1) {
        auto last = l1;
        while (last->next) {
            last = last->next;
        }
        auto it = l1;
        while (cycle1-- > 0) {
            if (!it) {
                throw std::runtime_error("Invalid input data");
            }
            it = it->next;
        }
        last->next = it;
    }

    std::set<shared_ptr<ListNode<int>>> common_nodes;
    auto it = common;
    while (it && common_nodes.count(it) == 0) {
        common_nodes.insert(it);
        it = it->next;
    }

    auto result = executor.Run([&] { return OverlappingLists(l0, l1); });

    if (!((common_nodes.empty() && result == nullptr) ||
          common_nodes.count(result) > 0)) {
        throw TestFailure("Invalid result");
    }
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "l0", "l1",
                                         "common", "cycle0", "cycle1"};
    return GenericTestMain(args, "do_lists_overlap.cc", "do_lists_overlap.tsv",
                           &OverlappingListsWrapper, DefaultComparator{},
                           param_names);
}
