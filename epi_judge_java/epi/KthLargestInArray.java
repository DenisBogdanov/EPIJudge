package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KthLargestInArray {
    // The numbering starts from one, i.e., if A = [3,1,-1,2] then
    // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
    // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
    @EpiTest(testDataFile = "kth_largest_in_array.tsv")
    public static int findKthLargest(int k, List<Integer> list) {
        int n = list.size();
        k = n - k;

        Random random = new Random();

        int left = 0;
        int right = n - 1;

        while (true) {
            int pivotIndex = left + random.nextInt(right - left + 1);
            int pivot = list.get(pivotIndex);

            Collections.swap(list, pivotIndex, left);
            int lessIndex = left + 1;

            for (int i = left + 1; i <= right; i++) {
                if (list.get(i) < pivot) {
                    Collections.swap(list, i, lessIndex);
                    lessIndex++;
                }
            }

            lessIndex--;
            Collections.swap(list, left, lessIndex);

            if (lessIndex == k) {
                return pivot;
            } else if (lessIndex < k) {
                left = lessIndex;
            } else {
                right = lessIndex;
            }
        }
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
