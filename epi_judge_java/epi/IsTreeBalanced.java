package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    if (tree == null) return true;
    return Math.abs(height(tree.left) - height(tree.right)) <= 1
            && isBalanced(tree.left)
            && isBalanced(tree.right);
  }

  private static int height(BinaryTreeNode<Integer> node) {
    if (node == null) return 0;
    return 1 + Math.max(height(node.left), height(node.right));
  }

  public static void main(String[] args) {
    System.exit(
            GenericTest
                    .runFromAnnotations(args, "IsTreeBalanced.java",
                            new Object() {
                            }.getClass().getEnclosingClass())
                    .ordinal());
  }
}
