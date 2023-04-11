package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class ApplyPermutation {
    public static void applyPermutation(List<Integer> perm, List<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n; i++) {
            int next = i;
            while (perm.get(next) >= 0) {
                Collections.swap(list, i, perm.get(next));
                int temp = perm.get(next);
                perm.set(next, perm.get(next) - perm.size());
                next = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            perm.set(i, perm.get(i) + n);
        }
    }

    @EpiTest(testDataFile = "apply_permutation.tsv")
    public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                        List<Integer> A) {
        applyPermutation(perm, A);
        return A;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
