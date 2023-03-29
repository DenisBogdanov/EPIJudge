package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchShiftedSortedArray {

    @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")
    public static int searchSmallest(List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;

        if (list.get(left) < list.get(right)) return left;

        while (left + 1 < right) {
            int midIndex = left + (right - left) / 2;

            if (list.get(midIndex) > list.get(left)) {
                left = midIndex;
            } else if (list.get(midIndex) < list.get(left)) {
                right = midIndex;
            } else {
                return list.get(left) < list.get(right) ? left : right;
            }
        }

        return right;
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
