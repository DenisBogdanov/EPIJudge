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

            x >>= 1;
            y <<= 1;
        }

        return result;
    }

    private static long add(long x, long y) {
        long result = 0;
        long carry = 0;
        long xCopy = x;
        long yCopy = y;
        long pow = 1;

        while (xCopy != 0 || yCopy != 0) {
            long xBit = x & pow;
            long yBit = y & pow;
            long tempCarry = (xBit & yBit) | (xBit & carry) | (yBit & carry);
            result |= xBit ^ yBit ^ carry;
            carry = tempCarry << 1;

            pow <<= 1;
            xCopy >>= 1;
            yCopy >>= 1;
        }

        return result | carry;
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
