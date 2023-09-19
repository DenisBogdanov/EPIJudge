#include <algorithm>
#include <iterator>
#include <memory>
#include <vector>

#include "list_node.h"
#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"
#include "test_framework/timed_executor.h"

using std::shared_ptr;

shared_ptr<ListNode<int>> ListPivoting(const shared_ptr<ListNode<int>> &list, int x) {
    shared_ptr<ListNode<int>> lessDummy(new ListNode<int>);
    shared_ptr<ListNode<int>> eqDummy(new ListNode<int>);
    shared_ptr<ListNode<int>> greaterDummy(new ListNode<int>);

    auto lessRunner = lessDummy;
    auto eqRunner = eqDummy;
    auto greaterRunner = greaterDummy;

    auto runner = list;
    while (runner != nullptr) {
        if (runner->data < x) {
            lessRunner->next = runner;
            lessRunner = lessRunner->next;
        } else if (runner->data == x) {
            eqRunner->next = runner;
            eqRunner = eqRunner->next;
        } else {
            greaterRunner->next = runner;
            greaterRunner = greaterRunner->next;
        }

        runner = runner->next;
    }

    greaterRunner->next = nullptr;
    eqRunner->next = greaterDummy->next;
    lessRunner->next = eqDummy->next;

    return lessDummy->next;
}

std::vector<int> ListToVector(const shared_ptr<ListNode<int>> &l) {
    std::vector<int> v;
    ListNode<int> *it = l.get();
    while (it) {
        v.push_back(it->data);
        it = it->next.get();
    }
    return v;
}

void ListPivotingWrapper(TimedExecutor &executor,
                         const shared_ptr<ListNode<int>> &l, int x) {
    std::vector<int> original = ListToVector(l);

    std::shared_ptr<ListNode<int>> pivoted_list =
            executor.Run([&] { return ListPivoting(l, x); });

    std::vector<int> pivoted = ListToVector(pivoted_list);
    enum class Mode {
        kLess, kEq, kGreater
    } mode = Mode::kLess;
    for (auto &i: pivoted) {
        switch (mode) {
            case Mode::kLess:
                if (i == x) {
                    mode = Mode::kEq;
                } else if (i > x) {
                    mode = Mode::kGreater;
                }
                break;
            case Mode::kEq:
                if (i < x) {
                    throw TestFailure("List is not pivoted");
                } else if (i > x) {
                    mode = Mode::kGreater;
                }
                break;
            case Mode::kGreater:
                if (i <= x) {
                    throw TestFailure("List is not pivoted");
                }
        }
    }
    std::sort(begin(original), end(original));
    std::sort(begin(pivoted), end(pivoted));
    if (original != pivoted) {
        throw TestFailure("Result list contains different values");
    }
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "l", "x"};
    return GenericTestMain(args, "pivot_list.cc", "pivot_list.tsv",
                           &ListPivotingWrapper, DefaultComparator{},
                           param_names);
}
