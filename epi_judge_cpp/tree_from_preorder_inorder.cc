#include <vector>
#include <unordered_map>

#include "binary_tree_node.h"
#include "test_framework/binary_tree_utils.h"
#include "test_framework/generic_test.h"

using namespace std;

unique_ptr<BinaryTreeNode<int>> helper(const vector<int> &preorder,
                                       size_t preorderStart, size_t preorderEnd,
                                       size_t inorderStart, size_t inorderEnd,
                                       const unordered_map<int, size_t> &nodeToInorderIndexMap) {

    if (preorderEnd <= preorderStart || inorderEnd <= inorderStart) return nullptr;

    size_t rootInorderIdx = nodeToInorderIndexMap.at(preorder[preorderStart]);
    size_t leftSubtreeSize = rootInorderIdx - inorderStart;

    return make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{
            preorder[preorderStart],
            helper(preorder, preorderStart + 1, preorderStart + 1 + leftSubtreeSize,
                   inorderStart, rootInorderIdx, nodeToInorderIndexMap),
            helper(preorder, preorderStart + 1 + leftSubtreeSize, preorderEnd,
                   rootInorderIdx + 1, inorderEnd, nodeToInorderIndexMap)
    });
}

unique_ptr<BinaryTreeNode<int>> BinaryTreeFromPreorderInorder(const vector<int> &preorder, const vector<int> &inorder) {
    unordered_map<int, size_t> nodeToInorderIndexMap;
    for (size_t i = 0; i < inorder.size(); i++) {
        nodeToInorderIndexMap.emplace(inorder[i], i);
    }

    return helper(preorder, 0, preorder.size(), 0, inorder.size(), nodeToInorderIndexMap);
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"preorder", "inorder"};
    return GenericTestMain(
            args, "tree_from_preorder_inorder.cc", "tree_from_preorder_inorder.tsv",
            &BinaryTreeFromPreorderInorder, DefaultComparator{}, param_names);
}
