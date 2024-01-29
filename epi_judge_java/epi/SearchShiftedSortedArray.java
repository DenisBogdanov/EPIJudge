package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchShiftedSortedArray {

    @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")
    public static int searchSmallest(List<Integer> nums) {
        if (nums.get(0) <= nums.get(nums.size() - 1)) return 0;

        int left = 0;
        int right = nums.size();

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            int midNum = nums.get(mid);
            if (midNum < nums.get(mid - 1)) {
                return mid;
            } else if (midNum > nums.get(0)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
