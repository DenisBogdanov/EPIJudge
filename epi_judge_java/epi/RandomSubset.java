package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomSubset {

    // Returns a random k-sized subset of {0, 1, ..., n - 1}.
    public static List<Integer> randomSubset(int n, int k) {
        List<Integer> list = IntStream.range(0, n).boxed().collect(Collectors.toList());
        OfflineSampling.randomSampling(k, list);

        List<Integer> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(list.get(i));
        }

        Random random = new Random();
        for (int i = 1; i < result.size(); i++) {
            int index = random.nextInt(i + 1);
            Collections.swap(result, i, index);
        }

        return result;
    }

    private static boolean randomSubsetRunner(TimedExecutor executor, int n,
                                              int k) throws Exception {
        List<List<Integer>> results = new ArrayList<>();

        executor.run(() -> {
            for (int i = 0; i < 1000000; ++i) {
                results.add(randomSubset(n, k));
            }
        });

        int totalPossibleOutcomes = RandomSequenceChecker.binomialCoefficient(n, k);
        List<Integer> A = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            A.add(i);
        }
        List<List<Integer>> combinations = new ArrayList<>();
        for (int i = 0; i < RandomSequenceChecker.binomialCoefficient(n, k); ++i) {
            combinations.add(RandomSequenceChecker.computeCombinationIdx(A, n, k, i));
        }
        List<Integer> sequence = new ArrayList<>();
        for (List<Integer> result : results) {
            Collections.sort(result);
            sequence.add(combinations.indexOf(result));
        }
        return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
                sequence, totalPossibleOutcomes, 0.01);
    }

    @EpiTest(testDataFile = "random_subset.tsv")
    public static void randomSubsetWrapper(TimedExecutor executor, int n, int k)
            throws Exception {
        RandomSequenceChecker.runFuncWithRetries(
                () -> randomSubsetRunner(executor, n, k));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RandomSubset.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
