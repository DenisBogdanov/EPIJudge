package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst {

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        return isValid(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isValid(BinaryTreeNode<Integer> tree, int left, int right) {
        if (tree == null) return true;
        if (tree.data < left || tree.data > right) return false;
        return isValid(tree.left, left, tree.data) && isValid(tree.right, tree.data, right);
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
