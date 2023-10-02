#include "binary_tree_node.h"
#include "test_framework/generic_test.h"

struct NodeInfo {
    bool isBalanced;
    int height;
};

NodeInfo calc(const unique_ptr<BinaryTreeNode<int>> &node);

bool IsBalanced(const unique_ptr<BinaryTreeNode<int>> &tree) {
    return calc(tree).isBalanced;
}

NodeInfo calc(const unique_ptr<BinaryTreeNode<int>> &node) {
    if (node == nullptr) return {true, 0};

    auto leftInfo = calc(node->left);
    if (!leftInfo.isBalanced) return {false, -1};

    auto rightInfo = calc(node->right);
    if (!rightInfo.isBalanced) return {false, -1};

    if (abs(leftInfo.height - rightInfo.height) > 1) return {false, -1};
    return {true, std::max(leftInfo.height, rightInfo.height) + 1};
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"tree"};
    return GenericTestMain(args, "is_tree_balanced.cc", "is_tree_balanced.tsv",
                           &IsBalanced, DefaultComparator{}, param_names);
}
