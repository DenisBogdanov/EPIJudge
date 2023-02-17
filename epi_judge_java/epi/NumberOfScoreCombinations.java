package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class NumberOfScoreCombinations {

    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    public static int numCombinationsForFinalScore(int finalScore, List<Integer> individualPlayScores) {
        int[] scoreToCombinationsDp = new int[finalScore + 1];
        scoreToCombinationsDp[0] = 1;

        for (Integer score : individualPlayScores) {
            for (int i = score; i <= finalScore; i++) {
                scoreToCombinationsDp[i] += scoreToCombinationsDp[i - score];
            }
        }

        return scoreToCombinationsDp[finalScore];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
