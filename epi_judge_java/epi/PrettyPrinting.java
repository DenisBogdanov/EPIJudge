package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PrettyPrinting {
    private static Map<Pair, Integer> cache;

    @EpiTest(testDataFile = "pretty_printing.tsv")
    public static int minimumMessiness(List<String> words, int lineLength) {
        cache = new HashMap<>();
        return helper(words, 1, words.get(0).length(), lineLength);
    }

    private static int helper(List<String> words, int index, int shift, int lineLength) {
        if (index == words.size()) {
            return (shift == 0 ? 0 : (lineLength - shift) * (lineLength - shift));
        }

        Pair pair = new Pair(index, shift);
        Integer result = cache.get(pair);
        if (result != null) return result;

        int remainsAfterAddition = lineLength - (shift + 1 + words.get(index).length());
        if (remainsAfterAddition >= 0) {
            // add word to curr line
            int result1 = helper(words, index + 1, shift + 1 + words.get(index).length(), lineLength);

            // starting from new line
            int result2 = (lineLength - shift) * (lineLength - shift);
            result2 += helper(words, index + 1, words.get(index).length(), lineLength);

            result = Math.min(result1, result2);
            cache.put(pair, result);
            return result;
        }

        result = (lineLength - shift) * (lineLength - shift) + helper(words, index + 1, words.get(index).length(), lineLength);
        cache.put(pair, result);
        return result;
    }

    private static class Pair {
        final int index;
        final int shift;

        public Pair(int index, int shift) {
            this.index = index;
            this.shift = shift;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, shift);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return index == pair.index && shift == pair.shift;
        }
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
