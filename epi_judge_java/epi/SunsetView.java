package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SunsetView {

    public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
        int currId = 0;
        Deque<Building> stack = new ArrayDeque<>();
        while (sequence.hasNext()) {
            int nextHeight = sequence.next();
            while (!stack.isEmpty() && stack.peek().height <= nextHeight) {
                stack.pop();
            }

            stack.push(new Building(currId, nextHeight));

            currId++;
        }

        return stack.stream().map(b -> b.id).collect(Collectors.toList());
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    public static List<Integer>
    examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
        return examineBuildingsWithSunset(sequence.iterator());
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SunsetView.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

    private static class Building {
        final int id;
        final int height;

        public Building(int id, int height) {
            this.id = id;
            this.height = height;
        }
    }
}
