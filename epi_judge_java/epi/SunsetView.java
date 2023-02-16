package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SunsetView {

    public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
        LinkedList<Building> result = new LinkedList<>();

        int index = 0;
        while (sequence.hasNext()) {
            Integer nextHeight = sequence.next();

            while (!result.isEmpty() && result.peek().height <= nextHeight) {
                result.pop();
            }

            result.push(new Building(index, nextHeight));
            index++;
        }

        return result.stream().map(b -> b.index).collect(Collectors.toList());
    }

    private static class Building {
        final int index;
        final int height;

        public Building(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    public static List<Integer> examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
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
}
