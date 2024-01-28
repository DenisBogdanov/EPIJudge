package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchFirstKey {

    @EpiTest(testDataFile = "search_first_key.tsv")
    public static int searchFirstOfK(List<Integer> nums, int k) {
        if (nums.isEmpty()) return -1;

        int left = -1;
        int right = nums.size() - 1;

        while (left + 1 < right) {
            int candidate = (left + right) / 2;

            if (nums.get(candidate) >= k) {
                right = candidate;
            } else {
                left = candidate;
            }
        }

        return nums.get(left + 1) == k ? left + 1 : -1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
