package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MaxProductAllButOne {

    @EpiTest(testDataFile = "max_product_all_but_one.tsv")
    public static int findBiggestProductNMinusOneProduct(List<Integer> nums) {
        int result = Integer.MIN_VALUE;
        int n = nums.size();
        int[] rightProducts = new int[n];
        rightProducts[n - 1] = 1;
        for (int i = n - 1; i >= 1; i--) {
            rightProducts[i - 1] = rightProducts[i] * nums.get(i);
        }

        int leftProduct = 1;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, leftProduct * rightProducts[i]);
            leftProduct *= nums.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxProductAllButOne.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
