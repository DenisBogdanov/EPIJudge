package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class KLargestInHeap {

    @EpiTest(testDataFile = "k_largest_in_heap.tsv")
    public static List<Integer> kLargestInBinaryHeap(List<Integer> nums, int k) {
        sort(nums, k);
        return nums.subList(0, k);
    }

    private static void sort(List<Integer> nums, int k) {
        sort(nums, 0, nums.size() - 1, k);
    }

    private static void sort(List<Integer> nums, int left, int right, int k) {
        if (left >= right) return;

        int pivotIndex = partition(nums, left, right);

        if (pivotIndex == k) {
            return;
        } else if (pivotIndex < k) {
            sort(nums, pivotIndex + 1, right, k);
        } else {
            sort(nums, left, pivotIndex - 1, k);
        }
    }

    private static int partition(List<Integer> nums, int left, int right) {
        int pivot = nums.get(left);

        int i = left + 1;
        int j = right;

        while (true) {
            while (i <= j) {
                if (nums.get(i) >= pivot) i++;
                else break;
            }

            while (i <= j) {
                if (nums.get(j) <= pivot) j--;
                else break;
            }

            if (i >= j) break;
            Collections.swap(nums, i++, j--);
        }

        Collections.swap(nums, left, j);
        return j;
    }

    @EpiTestComparator
    public static boolean comp(List<Integer> expected, List<Integer> result) {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
