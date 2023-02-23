package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class MinimumDistance3SortedArrays {

    @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")
    public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
        int n = sortedArrays.size();
        Queue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));

        for (int i = 0; i < n; i++) {
            minHeap.offer(new Element(sortedArrays.get(i).get(0), i, 0));
        }

        List<Element> elements = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Element polled = minHeap.poll();
            elements.add(polled);

            List<Integer> array = sortedArrays.get(polled.arrayIndex);
            if (polled.insideIndex + 1 < array.size()) {
                minHeap.offer(new Element(array.get(polled.insideIndex + 1), polled.arrayIndex, polled.insideIndex + 1));
            }
        }

        Set<Integer> seenArrays = new HashSet<>();
        TreeMap<Integer, Integer> valueToCountMap = new TreeMap<>();

        int currIndex = 0;

        while (seenArrays.size() < n) {
            Element element = elements.get(currIndex);
            if (!seenArrays.add(element.arrayIndex)) {
                Integer valueToRemove = sortedArrays.get(element.arrayIndex).get(element.insideIndex - 1);
                Integer count = valueToCountMap.get(valueToRemove);
                if (count == 1) {
                    valueToCountMap.remove(valueToRemove);
                } else {
                    valueToCountMap.put(valueToRemove, count - 1);
                }
            }

            valueToCountMap.merge(element.value, 1, Integer::sum);

            currIndex++;
        }

        int result = valueToCountMap.lastKey() - valueToCountMap.firstKey();


        while (currIndex < elements.size()) {
            Element element = elements.get(currIndex);
            Integer valueToRemove = sortedArrays.get(element.arrayIndex).get(element.insideIndex - 1);
            Integer count = valueToCountMap.get(valueToRemove);
            if (count == 1) {
                valueToCountMap.remove(valueToRemove);
            } else {
                valueToCountMap.put(valueToRemove, count - 1);
            }

            valueToCountMap.merge(element.value, 1, Integer::sum);
            result = Math.min(result, valueToCountMap.lastKey() - valueToCountMap.firstKey());

            currIndex++;
        }

        return result;
    }

    private static class Element {
        final int value;
        final int arrayIndex;
        final int insideIndex;

        public Element(int value, int arrayIndex, int insideIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.insideIndex = insideIndex;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "value=" + value +
                    ", arrayIndex=" + arrayIndex +
                    ", insideIndex=" + insideIndex +
                    '}';
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
