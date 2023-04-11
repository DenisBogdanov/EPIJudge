package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class ApplyPermutation {
    public static void applyPermutation(List<Integer> perm, List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < perm.size(); i++) {
            result[perm.get(i)] = list.get(i);
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, result[i]);
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
