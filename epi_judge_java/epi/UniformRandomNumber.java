package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniformRandomNumber {
    public static int uniformRandom(int lowerBound, int upperBound) {
        int diff = upperBound - lowerBound + 1;

        while (true) {
            int pow = 1;
            int result = 0;
            while (pow < diff) {
                result *= 2;
                result += zeroOneRandom();

                pow *= 2;
            }

            if (result < diff) {
                return lowerBound + result;
            }
        }
    }

    private static int zeroOneRandom() {
        Random gen = new Random();
        return gen.nextInt(2);
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
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
