package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PathSum {

    @EpiTest(testDataFile = "path_sum.tsv")
    public static boolean hasPathSum(BinaryTreeNode<Integer> tree, int remainingWeight) {
        remainingWeight -= tree.data;

        if (tree.left == null && tree.right == null) return remainingWeight == 0;

        return (tree.left != null && hasPathSum(tree.left, remainingWeight))
                || (tree.right != null && hasPathSum(tree.right, remainingWeight));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PathSum.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
