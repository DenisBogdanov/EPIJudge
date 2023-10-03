#include <vector>

#include "binary_tree_with_parent_prototype.h"
#include "test_framework/generic_test.h"

using namespace std;

vector<int> InorderTraversal(const unique_ptr<BinaryTreeNode<int>> &tree) {
    vector<int> result;
    if (tree == nullptr) return result;
    auto startNode = tree.get();
    while (startNode->left != nullptr) {
        startNode = startNode->left.get();
    }

    auto *successor = startNode;
    while (successor != nullptr) {
        result.push_back(successor->data);

        if (successor->right != nullptr) {
            auto *runner = successor->right.get();
            while (runner->left != nullptr) {
                runner = runner->left.get();
            }

            successor = runner;
        } else {
            auto *parent = successor->parent;
            auto *runner = successor;

            while (parent != nullptr) {
                if (runner == parent->left.get()) break;

                runner = parent;
                parent = parent->parent;
            }

            successor = parent;
        }
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"tree"};
    return GenericTestMain(args, "tree_with_parent_inorder.cc",
                           "tree_with_parent_inorder.tsv", &InorderTraversal,
                           DefaultComparator{}, param_names);
}
