package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniformRandomNumber {
  private static final double LOG_2_BASE_10 = Math.log10(2);

  private static int zeroOneRandom() {
    Random gen = new Random();
    return gen.nextInt(2);
  }

  public static int uniformRandom(int lowerBound, int upperBound) {
    int qty = upperBound - lowerBound + 1;

    int qtyOfThrows = (int) Math.ceil(Math.log10(qty) / LOG_2_BASE_10);

    int result = Integer.MAX_VALUE;

    while (result >= qty) {
      result = 0;
      for (int i = 0; i < qtyOfThrows; i++) {
        result *= 2;
        result += zeroOneRandom();
      }
    }

    return lowerBound + result;
  }
  private static boolean uniformRandomRunner(TimedExecutor executor,
                                             int lowerBound, int upperBound)
      throws Exception {
    List<Integer> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 100000; ++i) {
        results.add(uniformRandom(lowerBound, upperBound));
      }
    });

    List<Integer> sequence = new ArrayList<>();
    for (Integer result : results) {
      sequence.add(result - lowerBound);
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, upperBound - lowerBound + 1, 0.01);
  }

  @EpiTest(testDataFile = "uniform_random_number.tsv")
  public static void uniformRandomWrapper(TimedExecutor executor,
                                          int lowerBound, int upperBound)
      throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> uniformRandomRunner(executor, lowerBound, upperBound));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "UniformRandomNumber.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
