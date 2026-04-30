package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SmallestNonconstructibleValue {

    @EpiTest(testDataFile = "smallest_nonconstructible_value.tsv")
    public static int smallestNonconstructibleValue(List<Integer> nums) {
        nums.sort(null);
        int totalSum = 0;
        for (int num : nums) {
            if (totalSum + 1 < num) return totalSum + 1;
            totalSum += num;
        }
        return totalSum + 1;
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
