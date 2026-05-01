package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class TreeConnectLeaves {

    public static List<BinaryTreeNode<Integer>> createListOfLeaves(BinaryTreeNode<Integer> tree) {
        List<BinaryTreeNode<Integer>> ans = new ArrayList<>();
        addLeavesLeftToRight(tree, ans);
        return ans;
    }

    private static void addLeavesLeftToRight(BinaryTreeNode<Integer> tree, List<BinaryTreeNode<Integer>> ans) {
        if (tree == null) return;
        if (tree.left == null && tree.right == null) {
            ans.add(tree);
        } else {
            addLeavesLeftToRight(tree.left, ans);
            addLeavesLeftToRight(tree.right, ans);
        }
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
