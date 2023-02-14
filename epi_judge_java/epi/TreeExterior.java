package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeExterior {

    public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
        if (tree == null) return new ArrayList<>();

        List<BinaryTreeNode<Integer>> left = left(tree);
        if (isLeaf(left.get(left.size() - 1))) {
            left.remove(left.size() - 1);  // removing the leftmost leaf
        }

        List<BinaryTreeNode<Integer>> leaves = leaves(tree);

        List<BinaryTreeNode<Integer>> right = right(tree);
        right.remove(0);  // removing root
        if (!right.isEmpty() && isLeaf(right.get(right.size() - 1))) {
            right.remove(right.size() - 1);
        }

        Collections.reverse(right);

        return Stream.of(left, leaves, right)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return node.left == null && node.right == null;
    }

    private static List<BinaryTreeNode<Integer>> right(BinaryTreeNode<Integer> node) {
        List<BinaryTreeNode<Integer>> result = new ArrayList<>();
        result.add(node);

        if (node.right == null) return result;

        while (node.right != null || node.left != null) {
            if (node.right != null) {
                result.add(node.right);
                node = node.right;
            } else {
                result.add(node.left);
                node = node.left;
            }
        }

        return result;
    }

    private static List<BinaryTreeNode<Integer>> leaves(BinaryTreeNode<Integer> tree) {
        List<BinaryTreeNode<Integer>> result = new ArrayList<>();

        inorder(tree, result);

        return result;
    }

    private static void inorder(BinaryTreeNode<Integer> node, List<BinaryTreeNode<Integer>> result) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            result.add(node);
        } else {
            inorder(node.left, result);
            inorder(node.right, result);
        }
    }

    private static List<BinaryTreeNode<Integer>> left(BinaryTreeNode<Integer> node) {
        List<BinaryTreeNode<Integer>> result = new ArrayList<>();
        result.add(node);
        if (node.left == null) return result;

        while (node.left != null || node.right != null) {
            if (node.left != null) {
                result.add(node.left);
                node = node.left;
            } else {
                result.add(node.right);
                node = node.right;
            }
        }

        return result;
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
