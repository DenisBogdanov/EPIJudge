package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst {

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        return ok(tree, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean ok(BinaryTreeNode<Integer> node, long min, long max) {
        if (node == null) return true;
        if (node.data < min || node.data > max) return false;
        return ok(node.left, min, node.data) && ok(node.right, node.data, max);
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
