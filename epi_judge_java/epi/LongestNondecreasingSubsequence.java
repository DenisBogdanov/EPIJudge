package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestNondecreasingSubsequence {

    @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")
    public static int longestNondecreasingSubsequenceLength(List<Integer> nums) {
        List<Integer> dp = new ArrayList<>();
        for (int num : nums) {
            if (dp.isEmpty() || num >= dp.get(dp.size() - 1)) {
                dp.add(num);
            } else {
                int left = -1;
                int right = dp.size() - 1;
                while (left + 1 < right) {
                    int mid = (left + right) / 2;
                    if (dp.get(mid) > num) {
                        right = mid;
                    } else {
                        left = mid;
                    }
                }
                dp.set(right, num);
            }
        }
        return dp.size();
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
