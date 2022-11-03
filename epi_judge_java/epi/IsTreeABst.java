package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst {

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        return isBst(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBst(BinaryTreeNode<Integer> node, int min, Integer max) {
        if (node == null) return true;
        if (node.data < min || node.data > max) return false;
        return isBst(node.left, min, node.data)
                && isBst(node.right, node.data, max);
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
