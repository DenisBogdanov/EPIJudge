package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class NumberOfScoreCombinations {

    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    public static int numCombinationsForFinalScore(int finalScore, List<Integer> individualPlayScores) {
        int n = individualPlayScores.size();
        int[][] scoreToAmountOfCombinationsCache = new int[n][finalScore + 1];

        for (int i = 0; i < n; i++) {
            scoreToAmountOfCombinationsCache[i][0] = 1;
            for (int j = 1; j <= finalScore; j++) {
                scoreToAmountOfCombinationsCache[i][j] = i > 0 ? scoreToAmountOfCombinationsCache[i - 1][j] : 0;
                if (j >= individualPlayScores.get(i)) {
                    scoreToAmountOfCombinationsCache[i][j] += scoreToAmountOfCombinationsCache[i][j - individualPlayScores.get(i)];
                }
            }
        }

        return scoreToAmountOfCombinationsCache[n - 1][finalScore];
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
