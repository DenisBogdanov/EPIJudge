package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PowerXY {

    @EpiTest(testDataFile = "power_x_y.tsv")
    public static double power(double x, int y) {
        long p = y;
        if (p < 0) {
            x = 1 / x;
            p = -p;
        }
        double mult = x;
        double ans = 1;

        while (p > 0) {
            if (p % 2 != 0) {
                ans *= mult;
            }
            mult *= mult;
            p /= 2;
        }
        return ans;
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
