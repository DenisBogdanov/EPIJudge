package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SumRootToLeaf {
    private static int result = 0;

    @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
    public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
        result = 0;
        if (tree == null) return 0;
        calc(tree, 0);
        return result;
    }

    private static void calc(BinaryTreeNode<Integer> tree, int parentResult) {
        int currResult = parentResult * 2;
        currResult += tree.data;

        if (tree.left == null && tree.right == null) {
            result += currResult;
        } else {
            if (tree.left != null) {
                calc(tree.left, currResult);
            }

            if (tree.right != null) {
                calc(tree.right, currResult);
            }
        }
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
