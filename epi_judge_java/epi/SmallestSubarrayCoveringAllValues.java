package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallestSubarrayCoveringAllValues {

    public static Subarray findSmallestSequentiallyCoveringSubset(List<String> paragraph, List<String> keywords) {
        int n = keywords.size();

        Map<String, Integer> keywordToIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            keywordToIndexMap.put(keywords.get(i), i);
        }

        int[] latestOccurrence = new int[n];
        Arrays.fill(latestOccurrence, -1);

        int[] shortestSubarrayLength = new int[n];
        Arrays.fill(shortestSubarrayLength, Integer.MAX_VALUE);

        int shortestDistance = Integer.MAX_VALUE;
        Subarray result = new Subarray(-1, -1);

        for (int i = 0; i < paragraph.size(); i++) {
            Integer index = keywordToIndexMap.get(paragraph.get(i));
            if (index == null) continue;

            if (index == 0) {
                shortestSubarrayLength[0] = 1;
            } else if (shortestSubarrayLength[index - 1] != Integer.MAX_VALUE) {
                int distanceToPrev = i - latestOccurrence[index - 1];
                shortestSubarrayLength[index] = distanceToPrev + shortestSubarrayLength[index - 1];
            }

            latestOccurrence[index] = i;

            if (index == n - 1 && shortestSubarrayLength[n - 1] < shortestDistance) {
                shortestDistance = shortestSubarrayLength[n - 1];
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
