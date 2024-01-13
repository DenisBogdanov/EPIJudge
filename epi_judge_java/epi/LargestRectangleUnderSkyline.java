package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class LargestRectangleUnderSkyline {

    @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")
    public static int calculateLargestRectangle(List<Integer> heights) {
        Deque<Building> buildings = new ArrayDeque<>();

        int result = 0;

        for (int i = 0; i < heights.size(); i++) {
            int currHeight = heights.get(i);

            int indexOfRemoved = i;

            while (!buildings.isEmpty()) {
                Building peeked = buildings.peek();
                if (peeked.height == currHeight) {
                    result = Math.max(result, peeked.height * (i - peeked.index + 1));
                    break;
                } else if (peeked.height > currHeight) {
                    Building popped = buildings.pop();
                    indexOfRemoved = popped.index;
                    result = Math.max(result, currHeight * (i - popped.index + 1));
                } else {
                    result = Math.max(result, peeked.height * (i - peeked.index + 1));
                    buildings.push(new Building(indexOfRemoved, currHeight));
                    break;
                }
            }

            if (buildings.isEmpty()) {
                buildings.push(new Building(0, currHeight));
            }
        }

        return result;
    }

    private static class Building {
        final int index;
        final int height;

        public Building(int index, int height) {
            this.index = index;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Building{" +
                    "index=" + index +
                    ", height=" + height +
                    '}';
        }
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
