package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SumRootToLeaf {
    private static int totalSum = 0;

    @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
    public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
        totalSum = 0;
        if (tree == null) return totalSum;
        calc(tree, 0);
        return totalSum;
    }

    private static void calc(BinaryTreeNode<Integer> tree, int currSum) {
        if (tree.left == null && tree.right == null) {
            totalSum += currSum * 2 + tree.data;
            return;
        }

        if (tree.left != null) calc(tree.left, currSum * 2 + tree.data);
        if (tree.right != null) calc(tree.right, currSum * 2 + tree.data);
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
