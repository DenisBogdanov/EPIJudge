package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PrimitiveMultiply {

    @EpiTest(testDataFile = "primitive_multiply.tsv")
    public static long multiply(long x, long y) {
        long result = 0;

        while (x != 0) {
            if ((x & 1) != 0) {
                result = add(result, y);
            }

            x >>>= 1;
            y <<= 1;
        }

        return result;
    }

    private static long add(long a, long b) {
        long aCopy = a;
        long bCopy = b;

        long carryIn = 0;
        long result = 0;

        int currBit = 1;

        while (aCopy != 0 || bCopy != 0) {
            long aCurr = a & currBit;
            long bCurr = b & currBit;

            long carryOut = (aCurr & bCurr) | (aCurr & carryIn) | (bCurr & carryIn);

            result |= (aCurr ^ bCurr ^ carryIn);
            carryIn = carryOut << 1;

            currBit <<= 1;
            aCopy >>>= 1;
            bCopy >>>= 1;
        }


        return result | carryIn;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
