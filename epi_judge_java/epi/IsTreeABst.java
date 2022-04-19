package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst {

  @EpiTest(testDataFile = "is_tree_a_bst.tsv")
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return allBetween(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean allBetween(BinaryTreeNode<Integer> tree, int from, int to) {
    if (tree == null) {
      return true;
    }

    return tree.data >= from && tree.data <= to
        && allBetween(tree.left, from, tree.data)
        && allBetween(tree.right, tree.data, to);
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
