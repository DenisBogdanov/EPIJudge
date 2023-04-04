package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class TwoSortedArraysMerge {

    public static void mergeTwoSortedArrays(List<Integer> a, int m, List<Integer> b, int n) {
        int i1 = m - 1;
        int i2 = n - 1;

        int currIndex = m + n - 1;
        while (i1 >= 0 && i2 >= 0) {
            if (a.get(i1) > b.get(i2)) {
                a.set(currIndex, a.get(i1));
                i1--;
            } else {
                a.set(currIndex, b.get(i2));
                i2--;
            }

            currIndex--;
        }

        while (i2 >= 0) {
            a.set(currIndex, b.get(i2));
            i2--;
            currIndex--;
        }
    }

    @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
    public static List<Integer>
    mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
        mergeTwoSortedArrays(A, m, B, n);
        return A;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
