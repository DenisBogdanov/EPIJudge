package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KthLargestInArray {
    private static final Random RAND = new Random();

    // The numbering starts from one, i.e., if A = [3,1,-1,2] then
    // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
    // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
    @EpiTest(testDataFile = "kth_largest_in_array.tsv")
    public static int findKthLargest(int k, List<Integer> nums) {
        k = nums.size() - k;
        return find(k, nums, 0, nums.size());
    }

    private static int find(int k, List<Integer> nums, int left, int right) {
        int pivotIdx = RAND.nextInt(left, right);
        int pivot = nums.get(pivotIdx);
        Collections.swap(nums, left, pivotIdx);
        int writeIdx = left + 1;
        for (int i = left + 1; i < right; i++) {
            if (nums.get(i) <= pivot) {
                Collections.swap(nums, writeIdx, i);
                writeIdx++;
            }
        }
        writeIdx--;
        Collections.swap(nums, left, writeIdx);
        if (writeIdx == k) return pivot;
        if (writeIdx < k) return find(k, nums, writeIdx + 1, right);
        return find(k, nums, left, writeIdx);
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
