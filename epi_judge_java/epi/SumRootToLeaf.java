package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SumRootToLeaf {

  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    return dfs(tree, 0);
  }

  private static int dfs(BinaryTreeNode<Integer> tree, int acc) {
    if (tree == null) {
      return 0;
    }

    acc = acc * 2 + tree.data;

    if (tree.left == null && tree.right == null) {
      return acc;
    }

    return dfs(tree.left, acc) + dfs(tree.right, acc);
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
