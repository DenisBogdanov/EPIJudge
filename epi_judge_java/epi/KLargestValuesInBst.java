package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KLargestValuesInBst {

  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
//    return withInOrderTraversal(tree, k);


    List<Integer> result = new ArrayList<>(k);
    reverseOrder(tree, k, result);
    return result;
  }

  private static void reverseOrder(BstNode<Integer> tree, int k, List<Integer> list) {
    if (tree != null && list.size() < k) {
      reverseOrder(tree.right, k, list);
      if (list.size() < k) {
        list.add(tree.data);
        reverseOrder(tree.left, k, list);
      }
    }
  }

  private static List<Integer> withInOrderTraversal(BstNode<Integer> tree, int k) {
    List<Integer> result = new ArrayList<>();
    inOrder(tree, result);
    return result.subList(result.size() - k, result.size());
  }

  private static void inOrder(BstNode<Integer> tree, List<Integer> list) {
    if (tree != null) {
      inOrder(tree.left, list);
      list.add(tree.data);
      inOrder(tree.right, list);
    }
  }

  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
