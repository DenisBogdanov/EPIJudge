package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class SmallestNonconstructibleValue {

    @EpiTest(testDataFile = "smallest_nonconstructible_value.tsv")
    public static int smallestNonconstructibleValue(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) return 1;
        Collections.sort(nums);

        int currSum = 0;
        for (Integer num : nums) {
            if (num > currSum + 1) {
                return currSum + 1;
            }

            currSum += num;
        }

        return currSum + 1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SmallestNonconstructibleValue.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
