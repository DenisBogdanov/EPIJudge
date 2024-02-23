package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class MaxWaterTrappable {

    @EpiTest(testDataFile = "max_water_trappable.tsv")
    public static int calculateTrappingWater(List<Integer> heights) {
        int maxHeight = Collections.max(heights);
        int index = heights.indexOf(maxHeight);

        return calc(0, index, maxHeight, 1, heights)
                + calc(heights.size() - 1, index, maxHeight, -1, heights);
    }

    private static int calc(int start, int end, int maxHeight, int step, List<Integer> heights) {
        int result = 0;

        int maxSeen = 0;
        for (int i = start; i != end; i += step) {
            if (heights.get(i) < maxSeen) {
                result += maxSeen - heights.get(i);
            } else {
                maxSeen = heights.get(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxWaterTrappable.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
