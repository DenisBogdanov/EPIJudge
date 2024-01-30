package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class KthLargestInArray {
    // The numbering starts from one, i.e., if A = [3,1,-1,2] then
    // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
    // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
    @EpiTest(testDataFile = "kth_largest_in_array.tsv")
    public static int findKthLargest(int k, List<Integer> nums) {
        int result = find(nums, 0, nums.size() - 1, --k);
        return result;
    }

    private static int find(List<Integer> nums, int left, int right, int k) {
        if (left >= right) return nums.get(k);

        int pivotIndex = partition(nums, left, right, k);

        if (pivotIndex == k) {
            return nums.get(k);
        } else if (pivotIndex < k) {
            return find(nums, pivotIndex + 1, right, k);
        } else {
            return find(nums, left, pivotIndex - 1, k);
        }
    }

    private static int partition(List<Integer> nums, int left, int right, int k) {
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

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
