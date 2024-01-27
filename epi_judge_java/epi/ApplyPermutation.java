package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class ApplyPermutation {
    public static void applyPermutation(List<Integer> perm, List<Integer> nums) {
        for (int i = 0; i < nums.size(); i++) {
            int next = i;
            while (perm.get(next) >= 0) {
                Collections.swap(nums, i, perm.get(next));
                int temp = perm.get(next);
                perm.set(next, perm.get(next) - perm.size());
                next = temp;
            }
        }

        perm.replaceAll(num -> num + perm.size());
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
