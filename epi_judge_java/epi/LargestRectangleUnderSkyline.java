package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class LargestRectangleUnderSkyline {

    @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")
    public static int calculateLargestRectangle(List<Integer> heights) {
        Deque<Integer> indicesStack = new ArrayDeque<>();

        int result = 0;

        for (int i = 0; i < heights.size(); i++) {
            int currHeight = heights.get(i);


        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
