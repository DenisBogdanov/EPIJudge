package epi;

import epi.test_framework.*;

public class LowestCommonAncestor {
    public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> tree,
                                              BinaryTreeNode<Integer> node0,
                                              BinaryTreeNode<Integer> node1) {
        if (tree == null) return tree;
        if (node0 == node1) return node0;
        while (true) {
            if (tree.equals(node0) || tree.equals(node1)) return tree;
            int leftCount = containedCount(tree.left, node0, node1);
            if (leftCount == 0) {
                tree = tree.right;
            } else if (leftCount == 1) {
                return tree;
            } else if (leftCount == 2) {
                tree = tree.left;
            }
        }
    }

    private static int containedCount(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> n1, BinaryTreeNode<Integer> n2) {
        if (root == null) return 0;
        int ans = 0;
        if (root.data.equals(n1.data) || root.data.equals(n2.data)) ans++;
        return ans + containedCount(root.left, n1, n2) + containedCount(root.right, n1, n2);
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TimedExecutor executor,
                                 BinaryTreeNode<Integer> tree, Integer key0,
                                 Integer key1) throws Exception {
        BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

        BinaryTreeNode<Integer> result =
                executor.run(() -> lca(tree, node0, node1));

        if (result == null) {
            throw new TestFailure("Result can not be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
