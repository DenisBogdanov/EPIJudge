package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class BinomialCoefficients {

    @EpiTest(testDataFile = "binomial_coefficients.tsv")
    public static int computeBinomialCoefficient(int n, int k) {
        // 1: 1
        // 2: 1 1
        // 3: 1 2 1
        // 4: 1 3 3 1
        List<Integer> currDp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for (int j = 0; j < currDp.size() - 1; j++) {
                temp.add(currDp.get(j) + currDp.get(j + 1));
            }
            temp.add(1);
            currDp = temp;
        }
        return currDp.get(k);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BinomialCoefficients.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
