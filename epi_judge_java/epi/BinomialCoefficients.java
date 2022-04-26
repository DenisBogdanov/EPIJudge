package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class BinomialCoefficients {
  private static final Map<String, Integer> cache = new HashMap<>();

  @EpiTest(testDataFile = "binomial_coefficients.tsv")
  public static int computeBinomialCoefficient(int n, int k) {
    if (k == 0) return 1;
    if (k == 1) return n;
    if (n == k) return 1;

    int left = cache.computeIfAbsent((n - 1) + "#" + k, key -> computeBinomialCoefficient(n - 1, k));
    int right = cache.computeIfAbsent((n - 1) + "#" + (k - 1), key -> computeBinomialCoefficient(n - 1, k - 1));
    return left + right;
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
