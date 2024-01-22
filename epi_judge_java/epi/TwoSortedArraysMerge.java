package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class TwoSortedArraysMerge {

    public static void mergeTwoSortedArrays(List<Integer> a, int m, List<Integer> b, int n) {
        int currLast = m + n - 1;
        int i = m - 1;
        int j = n - 1;

        while (i >= 0 || j >= 0) {
            if (j == -1) {
                a.set(currLast, a.get(i));
                i--;
            } else if (i == -1) {
                a.set(currLast, b.get(j));
                j--;
            } else if (a.get(i) >= b.get(j)) {
                a.set(currLast, a.get(i));
                i--;
            } else {
                a.set(currLast, b.get(j));
                j--;
            }

            currLast--;
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
