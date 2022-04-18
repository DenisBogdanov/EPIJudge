package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeLevelOrder {

  @EpiTest(testDataFile = "tree_level_order.tsv")
  public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return List.of();
    }

    List<List<Integer>> result = new ArrayList<>();

    Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
    queue.add(tree);

    while (!queue.isEmpty()) {
      int size = queue.size();

      List<Integer> level = new ArrayList<>();

      for (int i = 0; i < size; i++) {
        BinaryTreeNode<Integer> polled = queue.poll();
        level.add(polled.data);

        if (polled.left != null) {
          queue.add(polled.left);
        }

        if (polled.right != null) {
          queue.add(polled.right);
        }
      }

      result.add(level);
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
