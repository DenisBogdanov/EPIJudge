package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SumRootToLeaf {

    @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
    public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
        return helper(0, tree);
    }

    private static int helper(int currResult, BinaryTreeNode<Integer> node) {
        if (node == null) return 0;
        int result = currResult * 2;
        result += node.data;

        if (node.left == null && node.right == null) return result;

        return helper(result, node.left) + helper(result, node.right);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
