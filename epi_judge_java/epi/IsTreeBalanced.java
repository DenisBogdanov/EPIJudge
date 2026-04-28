package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

    private static int findHeightDfs(BinaryTreeNode<Integer> tree) {
        if (tree == null) return 0;
        int left = findHeightDfs(tree.left);
        if (left == -1) return -1;
        int right = findHeightDfs(tree.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        return findHeightDfs(tree) != -1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
