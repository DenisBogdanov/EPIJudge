#include <memory>
#include <vector>

#include "binary_tree_node.h"
#include "test_framework/generic_test.h"

using namespace std;

vector<vector<int>> BinaryTreeDepthOrder(const unique_ptr<BinaryTreeNode<int>> &tree) {
    if (!tree) return {};
    queue<BinaryTreeNode<int> *> q({tree.get()});
    vector<vector<int>> result;

    while (!q.empty()) {
        size_t size = q.size();
        vector<int> level;

        for (size_t i{0}; i < size; i++) {
            auto popped = q.front();
            q.pop();

            if (popped->left) q.push(popped->left.get());
            if (popped->right) q.push(popped->right.get());

            level.emplace_back(popped->data);
        }

        result.emplace_back(level);
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"tree"};
    return GenericTestMain(args, "tree_level_order.cc", "tree_level_order.tsv",
                           &BinaryTreeDepthOrder, DefaultComparator{},
                           param_names);
}
