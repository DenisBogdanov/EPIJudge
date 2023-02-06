package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsTreeBalanced {
  private static final Map<BinaryTreeNode<Integer>, Integer> NODE_TO_HEIGHT_MAP = new HashMap<>();

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    if (tree == null) return true;
    return Math.abs(height(tree.left) - height(tree.right)) <= 1
            && isBalanced(tree.left)
            && isBalanced(tree.right);
  }

  private static int height(BinaryTreeNode<Integer> node) {
    Integer height = NODE_TO_HEIGHT_MAP.get(node);
    if (height != null) return height;

    if (node == null) return 0;
    int result = 1 + Math.max(height(node.left), height(node.right));
    NODE_TO_HEIGHT_MAP.put(node, result);

    return result;
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
