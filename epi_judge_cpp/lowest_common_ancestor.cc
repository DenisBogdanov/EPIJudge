#include <memory>

#include "binary_tree_node.h"
#include "test_framework/binary_tree_utils.h"
#include "test_framework/generic_test.h"
#include "test_framework/test_failure.h"
#include "test_framework/timed_executor.h"

int calcCount(const unique_ptr<BinaryTreeNode<int>> &tree, const unique_ptr<BinaryTreeNode<int>> &n1,
              const unique_ptr<BinaryTreeNode<int>> &n2);

using std::unique_ptr;

BinaryTreeNode<int> *Lca(const unique_ptr<BinaryTreeNode<int>> &tree,
                         const unique_ptr<BinaryTreeNode<int>> &node0,
                         const unique_ptr<BinaryTreeNode<int>> &node1) {

    if (node0->data == node1->data) return node0.get();
    auto runner = tree.get();

    while (true) {
        if (runner->data == node0->data || runner->data == node1->data) return runner;
        int foundLeft = calcCount(runner->left, node0, node1);
        if (foundLeft == 1) return runner;
        else if (foundLeft == 2) runner = (runner->left).get();
        else runner = (runner->right).get();
    }
}

int calcCount(const unique_ptr<BinaryTreeNode<int>> &tree, const unique_ptr<BinaryTreeNode<int>> &n1,
              const unique_ptr<BinaryTreeNode<int>> &n2) {

    if (tree == nullptr) return 0;
    int result = 0;
    if (tree->data == n1->data || tree->data == n2->data) result++;
    return result + calcCount(tree->left, n1, n2) + calcCount(tree->right, n1, n2);
}

int LcaWrapper(TimedExecutor &executor,
               const unique_ptr<BinaryTreeNode<int>> &tree, int key0,
               int key1) {
    const unique_ptr<BinaryTreeNode<int>> &node0 = MustFindNode(tree, key0);
    const unique_ptr<BinaryTreeNode<int>> &node1 = MustFindNode(tree, key1);

    auto result = executor.Run([&] { return Lca(tree, node0, node1); });

    if (!result) {
        throw TestFailure("Result can not be nullptr");
    }
    return result->data;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "tree", "key0", "key1"};
    return GenericTestMain(args, "lowest_common_ancestor.cc",
                           "lowest_common_ancestor.tsv", &LcaWrapper,
                           DefaultComparator{}, param_names);
}
