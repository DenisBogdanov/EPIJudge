package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst {

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        return helper(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean helper(BinaryTreeNode<Integer> node, int minValue, int maxValue) {
        if (node == null) return true;
        return node.data >= minValue
                && node.data <= maxValue
                && helper(node.left, minValue, node.data)
                && helper(node.right, node.data, maxValue);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
