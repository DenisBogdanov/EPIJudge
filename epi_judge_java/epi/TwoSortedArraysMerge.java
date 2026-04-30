package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class TwoSortedArraysMerge {

    public static void mergeTwoSortedArrays(List<Integer> a, int m, List<Integer> b, int n) {
        int writeIdx = m + n - 1;
        m--;
        n--;
        while (m >= 0 || n >= 0) {
            if (n < 0) break;
            if (m < 0 || a.get(m) < b.get(n)) {
                a.set(writeIdx, b.get(n));
                n--;
            } else {
                a.set(writeIdx, a.get(m));
                m--;
            }
            writeIdx--;
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
