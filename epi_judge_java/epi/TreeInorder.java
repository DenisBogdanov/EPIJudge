package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeInorder {

  @EpiTest(testDataFile = "tree_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> result = new ArrayList<>();
    BinaryTreeNode<Integer> current = tree;

    Deque<BinaryTreeNode<Integer>> stack = new LinkedList<>();

    while (!stack.isEmpty() || current != null) {
      if (current != null) {
        stack.push(current);
        current = current.left;
      } else {
        current = stack.pop();
        result.add(current.data);
        current = current.right;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
