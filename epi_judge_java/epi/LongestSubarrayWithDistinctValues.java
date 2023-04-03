package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestSubarrayWithDistinctValues {
    @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")

    public static int longestSubarrayWithDistinctEntries(List<Integer> list) {
        Set<Integer> seen = new HashSet<>();
        int result = 0;

        int start = -1;
        for (Integer num : list) {
            if (seen.contains(num)) {

                do {
                    start++;
                    seen.remove(list.get(start));
                } while (!list.get(start).equals(num));
            }

            seen.add(num);

            result = Math.max(result, seen.size());
        }

        return result;
    }

    public static void main(String[] args) {
        longestSubarrayWithDistinctEntries(List.of(20, 83, 90, 20, 98, 81, 67, 83, 77));
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
