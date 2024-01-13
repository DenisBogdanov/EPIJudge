package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MaxTrappedWater {

    @EpiTest(testDataFile = "max_trapped_water.tsv")
    public static int getMaxTrappedWater(List<Integer> heights) {
        int left = 0;
        int right = heights.size() - 1;

        int result = 0;

        while (left < right) {
            result = Math.max(result, Math.min(heights.get(left), heights.get(right)) * (right - left));
            if (heights.get(left) < heights.get(right)) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxTrappedWater.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
