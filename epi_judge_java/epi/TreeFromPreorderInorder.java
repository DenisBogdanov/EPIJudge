package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeFromPreorderInorder {

  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")
  public static BinaryTreeNode<Integer> binaryTreeFromPreorderInorder(
      List<Integer> preorder, List<Integer> inorder) {

    Map<Integer, Integer> nodeToInorderIndexMap = new HashMap<>();
    for (int i = 0; i < inorder.size(); i++) {
      nodeToInorderIndexMap.put(inorder.get(i), i);
    }

    return helper(preorder, 0, preorder.size(), 0, inorder.size(), nodeToInorderIndexMap);
  }

  private static BinaryTreeNode<Integer> helper(List<Integer> preorder, int preorderStart, int preorderEnd,
                                                int inorderStart, int inorderEnd, Map<Integer, Integer> nodeToInorderIndexMap) {

    if (preorderEnd <= preorderStart || inorderEnd <= inorderStart) {
      return null;
    }

    int rootInorderIndex = nodeToInorderIndexMap.get(preorder.get(preorderStart));
    int leftSubtreeSize = rootInorderIndex - inorderStart;

    return new BinaryTreeNode<>(
        preorder.get(preorderStart),
        helper(preorder, preorderStart + 1, preorderStart + 1 + leftSubtreeSize,
            inorderStart, rootInorderIndex, nodeToInorderIndexMap),
        helper(preorder, preorderStart + 1 + leftSubtreeSize, preorderEnd,
            rootInorderIndex + 1, inorderEnd, nodeToInorderIndexMap));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
