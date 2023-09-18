#include <set>
#include <stdexcept>

#include "list_node.h"
#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"
#include "test_framework/timed_executor.h"

shared_ptr<ListNode<int>> HasCycle(shared_ptr<ListNode<int>> &head);

shared_ptr<ListNode<int>> OverlappingNoCycleLists(shared_ptr<ListNode<int>> list1, shared_ptr<ListNode<int>> list2);

int Distance(shared_ptr<ListNode<int>> a, shared_ptr<ListNode<int>> b);

shared_ptr<ListNode<int>> OverlappingLists(shared_ptr<ListNode<int>> list1, shared_ptr<ListNode<int>> list2) {
    if (!list1 || !list2) return nullptr;

    auto cycleStart1 = HasCycle(list1);
    auto cycleStart2 = HasCycle(list2);

    if (cycleStart1 && cycleStart2) {
        auto temp = cycleStart2;
        do {
            temp = temp->next;
        } while (temp != cycleStart1 && temp != cycleStart2);

        if (temp != cycleStart1) return nullptr;

        int dist1 = Distance(list1, cycleStart1);
        int dist2 = Distance(list2, cycleStart2);

        int diff = dist1 - dist2;
        if (diff > 0) {
            for (int i = 0; i < diff; ++i) {
                list1 = list1->next;
            }
        } else {
            for (int i = 0; i < abs(diff); ++i) {
                list2 = list2->next;
            }
        }

        while (list1 != list2 && list1 != cycleStart1 && list2 != cycleStart2) {
            list1 = list1->next;
            list2 = list2->next;
        }

        return list1 == list2 ? list1 : cycleStart1;
    } else if (!cycleStart1 && !cycleStart2) {
        return OverlappingNoCycleLists(list1, list2);
    }

    return nullptr;
}

int Distance(shared_ptr<ListNode<int>> a, shared_ptr<ListNode<int>> b) {
    int result = 0;
    while (a != b) {
        a = a->next;
        result++;
    }

    return result;
}

shared_ptr<ListNode<int>> HasCycle(shared_ptr<ListNode<int>> &head) {
    auto slow = head;
    auto fast = head;

    while (fast && fast->next) {
        slow = slow->next;
        fast = fast->next->next;

        if (slow == fast) {
            slow = head;

            while (slow != fast) {
                slow = slow->next;
                fast = fast->next;
            }

            return slow;
        }
    }

    return nullptr;
}

shared_ptr<ListNode<int>> OverlappingNoCycleLists(
        shared_ptr<ListNode<int>> list1, shared_ptr<ListNode<int>> list2) {

    if (!list1 || !list2) return nullptr;

    int count = 0;
    auto r1 = list1;
    auto r2 = list2;

    while (count < 2) {
        r1 = r1->next;
        r2 = r2->next;

        if (!r1) {
            r1 = list2;
            count++;
        }

        if (!r2) {
            r2 = list1;
            count++;
        }
    }

    while (r1 != r2) {
        r1 = r1->next;
        r2 = r2->next;
    }

    return r1;
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
