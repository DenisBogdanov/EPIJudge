package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class IsStringDecomposableIntoWords {

    public static List<String> decomposeIntoDictionaryWords(String domain, Set<String> dictionary) {
        TrieArr trie = new TrieArr();
        for (String word : dictionary) {
            trie.insert(word);
        }

        ArrayList<String> result = new ArrayList<>();
        boolean[] isPossibleDp = new boolean[domain.length()];
        Arrays.fill(isPossibleDp, true);

        helper(domain, 0, trie, result, isPossibleDp);
        return result;
    }

    private static boolean helper(String domain, int index, TrieArr trie, List<String> result, boolean[] isPossibleDp) {
        if (index == domain.length()) return true;

        if (!isPossibleDp[index]) return false;

        for (int i = index; i < domain.length(); i++) {
            if (trie.search(domain.substring(index, i + 1))) {
                result.add(domain.substring(index, i + 1));
                if (helper(domain, i + 1, trie, result, isPossibleDp)) {
                    return true;
                } else {
                    isPossibleDp[i] = false;
                }

                result.remove(result.size() - 1);
            }
        }

        return isPossibleDp[index] = false;
    }

    private static class TrieArr {
        private final TrieNode head = new TrieNode();

        public void insert(String word) {
            TrieNode runner = this.head;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (runner.children[index] == null) {
                    runner.children[index] = new TrieNode();
                }

                runner = runner.children[index];
            }

            runner.isWordEnd = true;
        }

        public boolean search(String word) {
            TrieNode runner = this.head;
            for (char c : word.toCharArray()) {
                runner = runner.children[c - 'a'];
                if (runner == null) return false;
            }

            return runner.isWordEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode runner = this.head;
            for (char c : prefix.toCharArray()) {
                runner = runner.children[c - 'a'];
                if (runner == null) return false;
            }

            return true;
        }

        private static class TrieNode {
            final TrieNode[] children = new TrieNode[26];
            boolean isWordEnd;
        }
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
