package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntersectSortedArrays {

    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    public static List<Integer> intersectTwoSortedArrays(List<Integer> nums1, List<Integer> nums2) {
        List<Integer> result = new ArrayList<>();
        if (nums1.isEmpty() || nums2.isEmpty()) return result;

        if (Collections.binarySearch(nums2, nums1.get(0)) >= 0) result.add(nums1.get(0));

        for (int i = 1; i < nums1.size(); i++) {
            if (nums1.get(i).equals(nums1.get(i - 1))) continue;
            if (Collections.binarySearch(nums2, nums1.get(i)) >= 0) result.add(nums1.get(i));
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
