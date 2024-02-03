package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.Set;

public class CollatzChecker {

    @EpiTest(testDataFile = "collatz_checker.tsv")
    public static boolean testCollatzConjecture(int n) {
        Set<Long> checked = new HashSet<>();
        checked.add(1L);

        for (int i = 2; i <= n; i++) {
            Set<Long> curr = new HashSet<>();
            long num = i;
            while (!checked.contains(num)) {
                curr.add(num);
                if (num % 2 == 0) {
                    num /= 2;
                } else {
                    num = num * 3 + 1;
                }
            }

            checked.addAll(curr);
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
