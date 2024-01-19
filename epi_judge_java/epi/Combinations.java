package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    @EpiTest(testDataFile = "combinations.tsv")
    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) == k) {
                List<Integer> comb = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) {
                        comb.add(i + 1);
                    }
                }

                result.add(comb);
            }
        }

        return result;
    }

    @EpiTestComparator
    public static boolean comp(List<List<Integer>> expected,
                               List<List<Integer>> result) {
        if (result == null) {
            return false;
        }
        expected.sort(new LexicographicalListComparator<>());
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Combinations.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
