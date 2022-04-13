package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeSymmetric {

  @EpiTest(testDataFile = "is_tree_symmetric.tsv")
  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return true;
    }

    return isMirrored(tree.left, tree.right);
  }

  private static boolean isMirrored(BinaryTreeNode<Integer> t1, BinaryTreeNode<Integer> t2) {
    if (t1 == null && t2 == null) return true;
    if (t1 == null) return false;
    if (t2 == null) return false;

    return t1.data.equals(t2.data)
        && isMirrored(t1.left, t2.right)
        && isMirrored(t1.right, t2.left);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
