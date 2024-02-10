package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class MinimumDistance3SortedArrays {

    @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")
    public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
        TreeSet<Elem> elements = new TreeSet<>(Comparator.comparingInt(Elem::getValue).thenComparing(Elem::getArrIndex));
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < sortedArrays.size(); i++) {
            elements.add(new Elem(i, 0, sortedArrays.get(i).get(0)));
        }

        while (true) {
            result = Math.min(result, elements.last().value - elements.first().value);
            Elem first = elements.pollFirst();
            if (first.index + 1 == sortedArrays.get(first.arrIndex).size()) break;
            elements.add(new Elem(first.arrIndex, first.index + 1, sortedArrays.get(first.arrIndex).get(first.index + 1)));
        }

        return result;
    }

    private static class Elem {
        final int arrIndex;
        final int index;
        final int value;

        public int getArrIndex() {
            return arrIndex;
        }

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }

        public Elem(int arrIndex, int index, int value) {
            this.arrIndex = arrIndex;
            this.index = index;
            this.value = value;
        }
    }

    public static class ArrayData implements Comparable<ArrayData> {
        public int val;
        public int idx;

        public ArrayData(int idx, int val) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(ArrayData o) {
            int result = Integer.compare(val, o.val);
            if (result == 0) {
                result = Integer.compare(idx, o.idx);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumDistance3SortedArrays.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
