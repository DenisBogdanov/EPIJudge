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
        List<Integer> result = new ArrayList<>();
        inOrder(tree, result, k);
        return result;
    }

    private static void inOrder(BstNode<Integer> tree, List<Integer> result, int k) {
        if (tree == null) return;

        inOrder(tree.right, result, k);
        if (result.size() == k) return;
        result.add(tree.data);
        if (result.size() == k) return;
        inOrder(tree.left, result, k);
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
