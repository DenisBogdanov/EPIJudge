package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class TreeWithParentInorder {

  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    List<Integer> result = new ArrayList<>();

    BinaryTree<Integer> current = tree;
    BinaryTree<Integer> prev = null;

    while (current != null) {
      BinaryTree<Integer> next;
      if (current.parent == prev) {
        if (current.left != null) {
          next = current.left;
        } else {
          result.add(current.data);
          next = (current.right == null) ? current.parent : current.right;
        }
      } else if (current.left == prev) {
        result.add(current.data);
        next = (current.right == null) ? current.parent : current.right;
      } else {
        next = current.parent;
      }

      prev = current;
      current = next;
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
