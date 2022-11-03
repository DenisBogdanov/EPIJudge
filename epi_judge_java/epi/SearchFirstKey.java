package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchFirstKey {

    @EpiTest(testDataFile = "search_first_key.tsv")
    public static int searchFirstOfK(List<Integer> nums, int k) {
        int left = -1;
        int right = nums.size() - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < k) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (right < 0) return -1;
        return nums.get(right) == k ? right : -1;
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
