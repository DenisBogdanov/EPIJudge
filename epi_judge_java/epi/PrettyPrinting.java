package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class PrettyPrinting {

    @EpiTest(testDataFile = "pretty_printing.tsv")
    public static int minimumMessiness(List<String> words, int lineLength) {
        int[][] wordIndexToCurrLengthToMinMessinessDp = new int[words.size()][lineLength + 1];

        for (int[] row : wordIndexToCurrLengthToMinMessinessDp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        return helper(words, 0, 0, lineLength, wordIndexToCurrLengthToMinMessinessDp);
    }

    private static int helper(List<String> words, int index, int currLength, int lineLength, int[][] wordIndexToCurrLengthToMinMessinessDp) {
        if (index == words.size()) {
            return (lineLength - currLength) * (lineLength - currLength);
        }

        if (wordIndexToCurrLengthToMinMessinessDp[index][currLength] != Integer.MAX_VALUE) {
            return wordIndexToCurrLengthToMinMessinessDp[index][currLength];
        }

        int result = Integer.MAX_VALUE;

        if (currLength == 0) {
            result = helper(words, index + 1, words.get(index).length(), lineLength, wordIndexToCurrLengthToMinMessinessDp);
        } else if (currLength + 1 + words.get(index).length() <= lineLength) {
            result = helper(words, index + 1, currLength + 1 + words.get(index).length(), lineLength, wordIndexToCurrLengthToMinMessinessDp);
        }

        result = Math.min(result,
                (lineLength - currLength) * (lineLength - currLength) +
                        helper(words, index + 1, words.get(index).length(), lineLength, wordIndexToCurrLengthToMinMessinessDp));

        return wordIndexToCurrLengthToMinMessinessDp[index][currLength] = result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrettyPrinting.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
