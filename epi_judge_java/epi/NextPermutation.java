package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class NextPermutation {

    @EpiTest(testDataFile = "next_permutation.tsv")
    public static List<Integer> nextPermutation(List<Integer> perm) {
        int i;
        for (i = perm.size() - 1; i > 0; i--) {
            if (perm.get(i) > perm.get(i - 1)) break;
        }

        if (i == 0) return List.of();

        int a = i - 1;
        int b = perm.size() - 1;
        while (perm.get(b) <= perm.get(a)) {
            b--;
        }

        Collections.swap(perm, a, b);
        int left = a + 1;
        int right = perm.size() - 1;

        while (left < right) {
            Collections.swap(perm, left, right);
            left++;
            right--;
        }

        return perm;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
