package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseBits {

    @EpiTest(testDataFile = "reverse_bits.tsv")
    public static long reverseBits(long x) {
        int left = 0;
        int right = 63;
        while (left < right) {
            x = SwapBits.swapBits(x, left++, right--);
        }

        return x;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
