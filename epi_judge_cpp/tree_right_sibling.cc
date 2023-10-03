#include <memory>
#include <vector>

#include "test_framework/generic_test.h"
#include "test_framework/serialization_traits.h"
#include "test_framework/timed_executor.h"

using namespace std;
using test_framework::BinaryTreeSerializationTrait;

template<typename T>
struct BinaryTreeNode {
    T data;
    unique_ptr<BinaryTreeNode<T>> left, right;
    BinaryTreeNode<T> *next = nullptr;  // Populates this field.

    explicit BinaryTreeNode(T data) : data(data) {};
};

void ConstructRightSibling(BinaryTreeNode<int> *tree) {
    if (tree == nullptr) return;
    vector<BinaryTreeNode<int> *> level;
    level.push_back(tree);

    while (!level.empty()) {
        vector<BinaryTreeNode<int> *> temp;
        for (size_t i = 0; i < level.size(); i++) {
            auto curr = level[i];
            if (curr->left != nullptr) temp.push_back(curr->left.get());
            if (curr->right != nullptr) temp.push_back(curr->right.get());

            if (i + 1 < level.size()) {
                curr->next = level[i + 1];
            }
        }

        level = temp;
    }
}

namespace test_framework {
    template<>
    struct SerializationTrait<unique_ptr < BinaryTreeNode < int>>>
    : BinaryTreeSerializationTrait<unique_ptr < BinaryTreeNode < int>>, false> {
};
}  // namespace test_framework

std::vector<std::vector<int>> ConstructRightSiblingWrapper(
        TimedExecutor &executor, unique_ptr<BinaryTreeNode<int>> &tree) {
    executor.Run([&] { ConstructRightSibling(tree.get()); });

    std::vector<std::vector<int>> result;
    auto level_start = tree.get();
    while (level_start) {
        result.emplace_back();
        auto level_iter = level_start;
        while (level_iter) {
            result.back().push_back(level_iter->data);
            level_iter = level_iter->next;
        }
        level_start = level_start->left.get();
    }
    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "tree"};
    return GenericTestMain(
            args, "tree_right_sibling.cc", "tree_right_sibling.tsv",
            &ConstructRightSiblingWrapper, DefaultComparator{}, param_names);
}
