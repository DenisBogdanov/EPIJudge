package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class ThreeSum {

    @EpiTest(testDataFile = "three_sum.tsv")
    public static boolean hasThreeSum(List<Integer> nums, int t) {
        Collections.sort(nums);

        for (Integer firstNum : nums) {
            if (firstNum * 3 == t) return true;

            int left = 0;
            int right = nums.size() - 1;

            int necessarySum = t - firstNum;
            while (left < right) {
                if (nums.get(left) + nums.get(right) < necessarySum) {
                    left++;
                } else if (nums.get(left) + nums.get(right) == necessarySum) {
                    return true;
                } else {
                    right--;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
