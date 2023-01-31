package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PowerXY {

    @EpiTest(testDataFile = "power_x_y.tsv")
    public static double power(double x, int y) {
        if (y < 0) {
            x = 1 / x;
            y = -y;
        }

        double result = 1.0;

        double multiplier = x;

        while (y > 0) {
            if (y % 2 != 0) {
                result *= multiplier;
            }

            multiplier *= multiplier;
            y /= 2;
        }

        return result;
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
