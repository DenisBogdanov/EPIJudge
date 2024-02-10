package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;

import java.util.List;

public class BstFromSortedArray {

    public static BstNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer> list) {
        return build(list, 0, list.size());
    }

    private static BstNode<Integer> build(List<Integer> list, int left, int right) {
        if (left >= right) return null;
        int mid = left + (right - left) / 2;

        return new BstNode<>(list.get(mid),
                build(list, left, mid),
                build(list, mid + 1, right));
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
