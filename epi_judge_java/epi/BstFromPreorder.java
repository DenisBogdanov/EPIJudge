package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BstFromPreorder {
  private static Integer rootIndex;

  @EpiTest(testDataFile = "bst_from_preorder.tsv")
  public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence) {
    rootIndex = 0;
    return helper(preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static BstNode<Integer> helper(List<Integer> preorderSequence, int left, int right) {
    if (rootIndex == preorderSequence.size()) {
      return null;
    }

    Integer root = preorderSequence.get(rootIndex);
    if (root < left || root > right) {
      return null;
    }

    ++rootIndex;

    return new BstNode<>(root,
        helper(preorderSequence, left, root),
        helper(preorderSequence, root, right));
  }

  private static int firstGreaterIndex(List<Integer> list, int key) {
    int left = 0;
    int right = list.size() - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (list.get(mid) <= key) {
        left = mid + 1;
      } else {
        if (list.get(mid) > key && (mid == 0 || list.get(mid - 1) <= key)) {
          return mid;
        } else {
          right = mid;
        }
      }
    }

    return left;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
