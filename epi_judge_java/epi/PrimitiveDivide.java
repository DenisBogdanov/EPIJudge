package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PrimitiveDivide {

    @EpiTest(testDataFile = "primitive_divide.tsv")
    public static int divide(int x, int y) {
        int result = 0;
        int pow = 32;

        while (x >= y) {
            while ((y << pow) > x) {
                pow--;
            }

            result += 1 << pow;
            x -= (y << pow);
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
