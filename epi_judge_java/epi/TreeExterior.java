package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeExterior {

  public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
    List<BinaryTreeNode<Integer>> result = new LinkedList<>();

    if (tree != null) {
      result.add(tree);
      result.addAll(leftBoundaryAndLeaves(tree.left, true));
      result.addAll(rightBoundaryAndLeaves(tree.right, true));
    }

    return result;
  }

  private static List<BinaryTreeNode<Integer>> rightBoundaryAndLeaves(
      BinaryTreeNode<Integer> node, boolean isBoundary) {

    List<BinaryTreeNode<Integer>> result = new LinkedList<>();

    if (node != null) {
      result.addAll(rightBoundaryAndLeaves(node.left, isBoundary && node.right == null));
      result.addAll(rightBoundaryAndLeaves(node.right, isBoundary));

      if (isBoundary || isLeaf(node)) {
        result.add(node);
      }
    }

    return result;
  }

  private static List<BinaryTreeNode<Integer>> leftBoundaryAndLeaves(
      BinaryTreeNode<Integer> node, boolean isBoundary) {

    List<BinaryTreeNode<Integer>> result = new LinkedList<>();

    if (node != null) {
      if (isBoundary || isLeaf(node)) {
        result.add(node);
      }

      result.addAll(leftBoundaryAndLeaves(node.left, isBoundary));
      result.addAll(leftBoundaryAndLeaves(node.right, isBoundary && node.left == null));
    }

    return result;
  }

  private static boolean isLeaf(BinaryTreeNode<Integer> node) {
    return node.left == null && node.right == null;
  }

  private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
      throws TestFailure {
    if (L.contains(null)) {
      throw new TestFailure("Resulting list contains null");
    }
    List<Integer> output = new ArrayList<>();
    for (BinaryTreeNode<Integer> l : L) {
      output.add(l.data);
    }
    return output;
  }

  @EpiTest(testDataFile = "tree_exterior.tsv")
  public static List<Integer>
  exteriorBinaryTreeWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> exteriorBinaryTree(tree));

    return createOutputList(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeExterior.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
