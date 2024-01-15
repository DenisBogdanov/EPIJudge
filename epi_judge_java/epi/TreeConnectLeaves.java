package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class TreeConnectLeaves {

    public static List<BinaryTreeNode<Integer>> createListOfLeaves(BinaryTreeNode<Integer> tree) {
        if (tree == null) return List.of();

        List<BinaryTreeNode<Integer>> result = new ArrayList<>();

        inOrder(tree, result);

        return result;
    }

    private static void inOrder(BinaryTreeNode<Integer> node, List<BinaryTreeNode<Integer>> result) {
        if (node == null) return;

        inOrder(node.left, result);
        if (node.left == null && node.right == null) {
            result.add(node);
        }

        inOrder(node.right, result);
    }

    @EpiTest(testDataFile = "tree_connect_leaves.tsv")
    public static List<Integer>
    createListOfLeavesWrapper(TimedExecutor executor,
                              BinaryTreeNode<Integer> tree) throws Exception {
        List<BinaryTreeNode<Integer>> result =
                executor.run(() -> createListOfLeaves(tree));

        if (result.stream().anyMatch(x -> x == null || x.data == null)) {
            throw new TestFailure("Result can't contain null");
        }

        List<Integer> extractedRes = new ArrayList<>();
        for (BinaryTreeNode<Integer> x : result) {
            extractedRes.add(x.data);
        }
        return extractedRes;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeConnectLeaves.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
