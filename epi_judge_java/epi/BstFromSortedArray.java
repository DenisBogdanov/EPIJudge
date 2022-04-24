package epi;

import epi.test_framework.*;

import java.util.List;

public class BstFromSortedArray {

  public static BstNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer> list) {
    if (list.isEmpty()) return null;
    int mid = list.size() / 2;
    BstNode<Integer> root = new BstNode<>(list.get(mid));
    root.left = buildMinHeightBSTFromSortedArray(list.subList(0, mid));
    root.right = buildMinHeightBSTFromSortedArray(list.subList(mid + 1, list.size()));
    return root;
  }

  @EpiTest(testDataFile = "bst_from_sorted_array.tsv")
  public static int buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
                                                            List<Integer> A) throws Exception {
    BstNode<Integer> result =
        executor.run(() -> buildMinHeightBSTFromSortedArray(A));

    List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

    TestUtils.assertAllValuesPresent(A, inorder);
    BinaryTreeUtils.assertTreeIsBst(result);
    return BinaryTreeUtils.binaryTreeHeight(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromSortedArray.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
