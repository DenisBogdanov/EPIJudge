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

    public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                           Set<String> keywords) {
        Subarray result = new Subarray(0, paragraph.size() - 1);

        int left = 0;
        int right = paragraph.size();

        while (left + 1 < right) {
            int candidate = left + (right - left) / 2;

            Subarray subarray = satisfies(paragraph, new HashSet<>(keywords), candidate);
            if (subarray != null) {
                result = subarray;
                right = candidate;
            } else {
                left = candidate;
            }
        }

        return result;
    }

    private static Subarray satisfies(List<String> paragraph, HashSet<String> keywords, int candidate) {
        Map<String, Integer> currKeywordToCountMap = new HashMap<>();
        for (int i = 0; i < candidate; i++) {
            if (keywords.contains(paragraph.get(i))) {
                currKeywordToCountMap.merge(paragraph.get(i), 1, Integer::sum);
            }
        }

        if (currKeywordToCountMap.size() == keywords.size()) return new Subarray(0, candidate - 1);

        for (int i = candidate; i < paragraph.size(); i++) {
            if (keywords.contains(paragraph.get(i))) {
                currKeywordToCountMap.merge(paragraph.get(i), 1, Integer::sum);
            }

            if (keywords.contains(paragraph.get(i - candidate))) {
                Integer count = currKeywordToCountMap.get(paragraph.get(i - candidate));
                if (count == 1) {
                    currKeywordToCountMap.remove(paragraph.get(i - candidate));
                } else {
                    currKeywordToCountMap.put(paragraph.get(i - candidate), count - 1);
                }
            }

            if (currKeywordToCountMap.size() == keywords.size()) {
                return new Subarray(i - candidate + 1, i);
            }
        }

        return null;
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
