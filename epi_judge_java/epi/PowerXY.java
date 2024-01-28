package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PowerXY {

    @EpiTest(testDataFile = "power_x_y.tsv")
    public static double power(double x, int y) {
        boolean isNegative = y < 0;
        y = Math.abs(y);

        double result = 1.0;
        double pow = x;

        while (y > 0) {
            if (y % 2 != 0) {
                result *= pow;
            }

            pow *= pow;
            y /= 2;
        }

        return isNegative ? 1 / result : result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PowerXY.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
