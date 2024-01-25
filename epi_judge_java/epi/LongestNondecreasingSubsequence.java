package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.TreeMap;

public class LongestNondecreasingSubsequence {

    @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")
    public static int longestNondecreasingSubsequenceLength(List<Integer> nums) {
        TreeMap<Integer, Integer> numToLenMap = new TreeMap<>();
        numToLenMap.put(Integer.MIN_VALUE, 0);

        for (Integer num : nums) {
            int count = numToLenMap.floorEntry(num).getValue() + 1;

            var ceilingEntry = numToLenMap.ceilingEntry(num);
            while (ceilingEntry != null && ceilingEntry.getValue() <= count) {
                numToLenMap.remove(ceilingEntry.getKey());
                ceilingEntry = numToLenMap.ceilingEntry(num);
            }

            numToLenMap.put(num, count);
        }

        return numToLenMap.lastEntry().getValue();
    }

    public static void main(String[] args) {
        longestNondecreasingSubsequenceLength(List.of(5, 16, 5, 3, 9, 16, 20, 0, 6, 10, 12, 11, 6, 16, 10, 19, 20, 16, 13, 6));

        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
