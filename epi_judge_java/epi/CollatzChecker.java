package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class CollatzChecker {

    @EpiTest(testDataFile = "collatz_checker.tsv")
    public static boolean testCollatzConjecture(int n) {
        if (n <= 2) return true;
        boolean[] checked = new boolean[n + 1];
        checked[1] = true;
        checked[2] = true;

        for (long num = 3; num <= n; num++) {
            if (checked[(int) num]) continue;
            long currNum = num;

            List<Long> path = new ArrayList<>();
            path.add(currNum);

            while (currNum >= num && (currNum > n || !checked[(int) currNum])) {
                if (currNum % 2 == 0) {
                    currNum /= 2;
                    path.add(currNum);
                } else {
                    currNum = currNum * 3 + 1;
                }
            }

            for (long p : path) {
                if (p <= n) {
                    checked[(int) p] = true;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CollatzChecker.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
