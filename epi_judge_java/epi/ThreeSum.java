package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class ThreeSum {

    @EpiTest(testDataFile = "three_sum.tsv")
    public static boolean hasThreeSum(List<Integer> list, int t) {
        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            int remainingSum = t - list.get(i);

            int left = i;
            int right = list.size() - 1;

            while (left <= right) {
                int candidateSum = list.get(left) + list.get(right);

                if (candidateSum > remainingSum) {
                    right--;
                } else if (candidateSum < remainingSum) {
                    left++;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
