#include "binary_tree_node.h"
#include "test_framework/generic_test.h"

using namespace std;

int height(const unique_ptr<BinaryTreeNode<int>> &tree) {
    if (tree == nullptr) return 0;
    return max(height(tree->left), height(tree->right)) + 1;
}

bool IsBalanced(const unique_ptr<BinaryTreeNode<int>> &tree) {
    if (tree == nullptr) return true;
    if (abs(height(tree->left) - height(tree->right)) > 1) return false;
    return IsBalanced(tree->left) && IsBalanced(tree->right);
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"tree"};
    return GenericTestMain(args, "is_tree_balanced.cc", "is_tree_balanced.tsv",
                           &IsBalanced, DefaultComparator{}, param_names);
}
