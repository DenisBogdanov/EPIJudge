#include "binary_tree_with_parent_prototype.h"
#include "test_framework/binary_tree_utils.h"
#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"
#include "test_framework/timed_executor.h"

BinaryTreeNode<int> *Lca(const unique_ptr<BinaryTreeNode<int>> &n1,
                         const unique_ptr<BinaryTreeNode<int>> &n2) {

    int h1 = 0;
    auto r1 = n1.get();
    while (r1 != nullptr) {
        r1 = r1->parent;
        h1++;
    }

    int h2 = 0;
    auto r2 = n2.get();
    while (r2 != nullptr) {
        r2 = r2->parent;
        h2++;
    }

    int diff = h2 - h1;
    r1 = n1.get();
    r2 = n2.get();
    if (diff > 0) {
        for (int i = 0; i < diff; ++i) {
            r2 = r2->parent;
        }
    } else {
        for (int i = 0; i < -diff; ++i) {
            r1 = r1->parent;
        }
    }

    while (r1->data != r2->data) {
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
