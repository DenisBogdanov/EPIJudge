#include "binary_tree_with_parent_prototype.h"
#include "test_framework/binary_tree_utils.h"
#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"
#include "test_framework/timed_executor.h"

BinaryTreeNode<int> *Lca(const unique_ptr<BinaryTreeNode<int>> &node1,
                         const unique_ptr<BinaryTreeNode<int>> &node2) {

    auto r1 = node1.get();
    int h1 = 0;
    while (r1 != nullptr) {
        h1++;
        r1 = r1->parent;
    }
    auto r2 = node2.get();
    int h2 = 0;
    while (r2 != nullptr) {
        h2++;
        r2 = r2->parent;
    }

    r1 = node1.get();
    r2 = node2.get();
    int diff = h1 - h2;
    if (diff > 0) {
        while (diff--) {
            r1 = r1->parent;
        }
    } else if (diff < 0) {
        diff = -diff;
        while (diff--) {
            r2 = r2->parent;
        }
    }

    while (r1 != r2) {
        r1 = r1->parent;
        r2 = r2->parent;
    }
    return r1;
}

int LcaWrapper(TimedExecutor &executor,
               const unique_ptr<BinaryTreeNode<int>> &tree, int key0,
               int key1) {
    const unique_ptr<BinaryTreeNode<int>> &node0 = MustFindNode(tree, key0);
    const unique_ptr<BinaryTreeNode<int>> &node1 = MustFindNode(tree, key1);

    auto result = executor.Run([&] { return Lca(node0, node1); });

    if (!result) {
        throw TestFailure("Result can not be nullptr");
    }
    return result->data;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "tree", "key0", "key1"};
    return GenericTestMain(args, "lowest_common_ancestor_with_parent.cc",
                           "lowest_common_ancestor.tsv", &LcaWrapper,
                           DefaultComparator{}, param_names);
}
