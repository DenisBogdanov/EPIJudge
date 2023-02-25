package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class LongestNondecreasingSubsequence {

    @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")
    public static int longestNondecreasingSubsequenceLength(List<Integer> list) {
        List<Integer> longestSoFar = new ArrayList<>();

        for (Integer num : list) {
            if (longestSoFar.isEmpty() || num >= longestSoFar.get(longestSoFar.size() - 1)) {
                longestSoFar.add(num);
            } else {
                int index = binarySearch(longestSoFar, num);
                longestSoFar.set(index, num);
            }
        }

        return longestSoFar.size();
    }

    private static int binarySearch(List<Integer> nums, int num) {
        int left = -1;
        int right = nums.size() - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums.get(mid) > num) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
