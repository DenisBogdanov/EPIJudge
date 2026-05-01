package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class NextPermutation {

    @EpiTest(testDataFile = "next_permutation.tsv")
    public static List<Integer> nextPermutation(List<Integer> perm) {
        // 1234 -> 1243
        // 4321 -> null
        // 1385 -> 1538
        // 1685 -> 1856
        int left = perm.size() - 2;
        int currMax = perm.get(left + 1);
        while (left >= 0 && perm.get(left) >= currMax) {
            currMax = perm.get(left);
            left--;
        }
        if (left < 0) return List.of();
        int right = left + 1;
        while (right < perm.size() && perm.get(right) > perm.get(left)) right++;
        right--;
        Collections.swap(perm, left, right);
        reverse(perm, left + 1, perm.size());
        return perm;
    }

    private static void reverse(List<Integer> nums, int left, int right) {
        right--;
        while (left < right) {
            Collections.swap(nums, left, right);
            left++;
            right--;
        }
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
