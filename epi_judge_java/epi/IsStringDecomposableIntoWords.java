package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class IsStringDecomposableIntoWords {

    public static List<String> decomposeIntoDictionaryWords(String domain, Set<String> dictionary) {
        int[] prev = new int[domain.length() + 1];
        Arrays.fill(prev, -1);

        if (isSuccess(domain, 0, prev, dictionary)) {
            List<String> result = new ArrayList<>();
            int curr = domain.length();
            do {
                int start = prev[curr];
                result.add(domain.substring(start, curr));
                curr = start;
            } while (prev[curr] != -1);

            Collections.reverse(result);
            return result;
        }

        return List.of();
    }

    private static boolean isSuccess(String domain, int start, int[] prev, Set<String> dictionary) {
        if (start == domain.length()) return true;

        for (int end = start; end < domain.length(); end++) {
            if (prev[end + 1] != -1) continue;
            if (dictionary.contains(domain.substring(start, end + 1))) {
                prev[end + 1] = start;
                if (isSuccess(domain, end + 1, prev, dictionary)) {
                    return true;
                }
            }
        }

        return false;
    }

    @EpiTest(testDataFile = "is_string_decomposable_into_words.tsv")
    public static void decomposeIntoDictionaryWordsWrapper(TimedExecutor executor,
                                                           String domain,
                                                           Set<String> dictionary,
                                                           Boolean decomposable)
            throws Exception {
        List<String> result =
                executor.run(() -> decomposeIntoDictionaryWords(domain, dictionary));

        if (!decomposable) {
            if (!result.isEmpty()) {
                throw new TestFailure("domain is not decomposable");
            }
            return;
        }

        if (result.stream().anyMatch(s -> !dictionary.contains(s))) {
            throw new TestFailure("Result uses words not in dictionary");
        }

        if (!String.join("", result).equals(domain)) {
            throw new TestFailure("Result is not composed into domain");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringDecomposableIntoWords.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
