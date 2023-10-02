#include "binary_tree_node.h"
#include "test_framework/generic_test.h"

int calc(const unique_ptr<BinaryTreeNode<int>> &tree, int currSum);

int SumRootToLeaf(const unique_ptr<BinaryTreeNode<int>> &tree) {
    return calc(tree, 0);
}

int calc(const unique_ptr<BinaryTreeNode<int>> &tree, int currSum) {
    if (tree == nullptr) return 0;

    currSum = currSum * 2 + tree->data;

    if (tree->left == nullptr && tree->right == nullptr) return currSum;

    return calc(tree->left, currSum) + calc(tree->right, currSum);
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"tree"};
    return GenericTestMain(args, "sum_root_to_leaf.cc", "sum_root_to_leaf.tsv",
                           &SumRootToLeaf, DefaultComparator{}, param_names);
}
