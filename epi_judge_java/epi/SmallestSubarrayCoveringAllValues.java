package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallestSubarrayCoveringAllValues {

    public static Subarray findSmallestSequentiallyCoveringSubset(List<String> paragraph,
                                                                  List<String> keywords) {
        Subarray result = new Subarray(-1, -1);
        Map<String, Integer> keywordToIndexMap = new HashMap<>();
        List<Integer> latestOccurrence = new ArrayList<>(keywords.size());
        List<Integer> shortestSubarrayLength = new ArrayList<>(keywords.size());

        for (int i = 0; i < keywords.size(); i++) {
            keywordToIndexMap.put(keywords.get(i), i);
            latestOccurrence.add(-1);
            shortestSubarrayLength.add(Integer.MAX_VALUE);
        }

        int shortestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < paragraph.size(); i++) {
            Integer index = keywordToIndexMap.get(paragraph.get(i));
            if (index == null) continue;

            if (index == 0) {
                shortestSubarrayLength.set(0, 1);
            } else if (shortestSubarrayLength.get(index - 1) != Integer.MAX_VALUE) {
                int distanceToPrev = i - latestOccurrence.get(index - 1);
                shortestSubarrayLength.set(index, distanceToPrev + shortestSubarrayLength.get(index - 1));
            }

            latestOccurrence.set(index, i);

            if (index == keywords.size() - 1
                    && shortestSubarrayLength.get(shortestSubarrayLength.size() - 1) < shortestDistance) {

                shortestDistance = shortestSubarrayLength.get(shortestSubarrayLength.size() - 1);
                result.start = i - shortestDistance + 1;
                result.end = i;
            }
        }

        return result;
    }

    public static class Subarray {
        // Represent subarray by starting and ending indices, inclusive.
        public Integer start;
        public Integer end;

        public Subarray(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
    public static int findSmallestSequentiallyCoveringSubsetWrapper(
            TimedExecutor executor, List<String> paragraph, List<String> keywords)
            throws Exception {
        Subarray result = executor.run(
                () -> findSmallestSequentiallyCoveringSubset(paragraph, keywords));

        int kwIdx = 0;
        if (result.start < 0) {
            throw new TestFailure("Subarray start index is negative");
        }
        int paraIdx = result.start;

        while (kwIdx < keywords.size()) {
            if (paraIdx >= paragraph.size()) {
                throw new TestFailure("Not all keywords are in the generated subarray");
            }
            if (paraIdx >= paragraph.size()) {
                throw new TestFailure("Subarray end index exceeds array size");
            }
            if (paragraph.get(paraIdx).equals(keywords.get(kwIdx))) {
                kwIdx++;
            }
            paraIdx++;
        }
        return result.end - result.start + 1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SmallestSubarrayCoveringAllValues.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
