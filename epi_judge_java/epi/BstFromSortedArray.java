package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;

import java.util.List;

public class BstFromSortedArray {

    public static BstNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer> list) {
        return helper(list, 0, list.size() - 1);
    }

    private static BstNode<Integer> helper(List<Integer> list, int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;

        BstNode<Integer> root = new BstNode<>(list.get(mid));
        root.left = helper(list, left, mid - 1);
        root.right = helper(list, mid + 1, right);

        return root;
    }

    @EpiTest(testDataFile = "bst_from_sorted_array.tsv")
    public static int
    buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
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
