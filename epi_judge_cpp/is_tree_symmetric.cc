#include "binary_tree_node.h"
#include "test_framework/generic_test.h"

bool eq(unique_ptr<BinaryTreeNode<int>> &node1, unique_ptr<BinaryTreeNode<int>> &node2);

bool IsSymmetric(const unique_ptr<BinaryTreeNode<int>> &tree) {
    if (tree == nullptr) return true;
    return eq(tree->left, tree->right);
}

bool eq(unique_ptr<BinaryTreeNode<int>> &node1, unique_ptr<BinaryTreeNode<int>> &node2) {
    if (node1 == nullptr || node2 == nullptr) return node1 == node2;

    if (node1->data != node2->data) return false;

    return eq(node1->left, node2->right) && eq(node1->right, node2->left);
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"tree"};
    return GenericTestMain(args, "is_tree_symmetric.cc", "is_tree_symmetric.tsv",
                           &IsSymmetric, DefaultComparator{}, param_names);
}
