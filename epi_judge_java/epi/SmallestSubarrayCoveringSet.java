package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SmallestSubarrayCoveringSet {

    public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph, Set<String> keywords) {
        Map<String, Integer> wordToCountMap = new HashMap<>();
        for (String keyword : keywords) {
            wordToCountMap.put(keyword, 0);
        }

        int start = 0;
        int currCount = 0;
        Subarray result = null;

        for (int end = 0; end < paragraph.size(); end++) {
            String currWord = paragraph.get(end);
            if (wordToCountMap.containsKey(currWord)) {
                Integer count = wordToCountMap.merge(currWord, 1, Integer::sum);
                if (count == 1) {
                    currCount++;

                    while (currCount == keywords.size()) {
                        if (result == null || result.end - result.start > end - start) {
                            result = new Subarray(start, end);
                        }

                        if (wordToCountMap.containsKey(paragraph.get(start))) {
                            Integer decreasedCount = wordToCountMap.merge(paragraph.get(start), -1, Integer::sum);
                            if (decreasedCount == 0) {
                                currCount--;
                            }
                        }

                        start++;
                    }
                }
            }
        }


        return result;
    }

    // Represent subarray by starting and ending indices, inclusive.
    private static class Subarray {
        public Integer start;
        public Integer end;

        public Subarray(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
    public static int findSmallestSubarrayCoveringSetWrapper(
            TimedExecutor executor, List<String> paragraph, Set<String> keywords)
            throws Exception {
        Set<String> copy = new HashSet<>(keywords);

        Subarray result = executor.run(
                () -> findSmallestSubarrayCoveringSet(paragraph, keywords));

        if (result.start < 0 || result.start >= paragraph.size() ||
                result.end < 0 || result.end >= paragraph.size() ||
                result.start > result.end)
            throw new TestFailure("Index out of range");

        for (int i = result.start; i <= result.end; i++) {
            copy.remove(paragraph.get(i));
        }

        if (!copy.isEmpty()) {
            throw new TestFailure("Not all keywords are in the range");
        }
        return result.end - result.start + 1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SmallestSubarrayCoveringSet.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
