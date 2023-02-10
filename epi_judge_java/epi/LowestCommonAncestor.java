package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayDeque;
import java.util.Queue;

public class LowestCommonAncestor {

    public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> tree,
                                              BinaryTreeNode<Integer> node0,
                                              BinaryTreeNode<Integer> node1) {

        if (node0 == node1) return node0;
        if (tree == null || tree == node0 || tree == node1) return tree;

        int leftCount = count(tree.left, node0, node1);
        if (leftCount == 2) return lca(tree.left, node0, node1);
        if (leftCount == 1) return tree;
        return lca(tree.right, node0, node1);
    }

    private static int count(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> node0, BinaryTreeNode<Integer> node1) {
        int result = 0;
        if (root == null) return result;

        Queue<BinaryTreeNode<Integer>> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            BinaryTreeNode<Integer> polled = q.poll();
            if (polled == node0 || polled == node1) result++;
            if (result == 2) return result;

            if (polled.left != null) q.offer(polled.left);
            if (polled.right != null) q.offer(polled.right);
        }

        return result;
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
