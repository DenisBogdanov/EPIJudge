package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class TreeExterior {

    public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
        List<BinaryTreeNode<Integer>> result = new ArrayList<>();
        if (tree == null) return result;

        result.add(tree);
        left(tree.left, result);
        leaves(tree.left, result);
        leaves(tree.right, result);
        right(tree.right, result);

        return result;
    }

    private static void left(BinaryTreeNode<Integer> node, List<BinaryTreeNode<Integer>> result) {
        if (node == null || isLeaf(node)) return;

        result.add(node);

        if (node.left != null) left(node.left, result);
        else left(node.right, result);
    }

    private static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return node.left == null && node.right == null;
    }

    private static void leaves(BinaryTreeNode<Integer> node, List<BinaryTreeNode<Integer>> result) {
        if (node == null) return;

        if (isLeaf(node)) {
            result.add(node);
        }

        leaves(node.left, result);
        leaves(node.right, result);
    }

    private static void right(BinaryTreeNode<Integer> node, List<BinaryTreeNode<Integer>> result) {
        if (node == null || isLeaf(node)) return;

        if (node.right != null) right(node.right, result);
        else right(node.left, result);

        result.add(node);
    }

    private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
            throws TestFailure {
        if (L.contains(null)) {
            throw new TestFailure("Resulting list contains null");
        }
        List<Integer> output = new ArrayList<>();
        for (BinaryTreeNode<Integer> l : L) {
            output.add(l.data);
        }
        return output;
    }

    @EpiTest(testDataFile = "tree_exterior.tsv")
    public static List<Integer>
    exteriorBinaryTreeWrapper(TimedExecutor executor,
                              BinaryTreeNode<Integer> tree) throws Exception {
        List<BinaryTreeNode<Integer>> result =
                executor.run(() -> exteriorBinaryTree(tree));

        return createOutputList(result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeExterior.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
