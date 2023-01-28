package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ClosestIntSameWeight {

    @EpiTest(testDataFile = "closest_int_same_weight.tsv")
    public static long closestIntSameBitCount(long x) {
        for (int i = 0; i < 63; i++) {
            if ((x & (1L << i)) == 0 && (x & (1L << (i + 1))) != 0) {
                x ^= (1L << i) | (1L << (i + 1));
                return x;
            }

            if ((x & (1L << i)) != 0 && (x & (1L << (i + 1))) == 0) {
                x ^= (1L << i) | (1L << (i + 1));
                return x;
            }
        }

        return x;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
