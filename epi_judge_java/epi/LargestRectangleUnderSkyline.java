package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class LargestRectangleUnderSkyline {

    @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")
    public static int calculateLargestRectangle(List<Integer> heights) {
        int result = 0;

        Deque<Integer> indicesStack = new ArrayDeque<>();
        for (int i = 0; i < heights.size(); i++) {
            while (!indicesStack.isEmpty() && heights.get(indicesStack.peek()) >= heights.get(i)) {
                Integer popped = indicesStack.pop();
                int height = heights.get(popped);
                int width;
                if (indicesStack.isEmpty()) {
                    width = i;
                } else {
                    width = i - indicesStack.peek() - 1;
                }

                result = Math.max(result, height * width);
            }

            indicesStack.push(i);
        }

        while (!indicesStack.isEmpty()) {
            Integer popped = indicesStack.pop();
            int height = heights.get(popped);
            int width;

            if (indicesStack.isEmpty()) {
                width = heights.size();
            } else {
                width = heights.size() - indicesStack.peek() - 1;
            }

            result = Math.max(result, height * width);
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
