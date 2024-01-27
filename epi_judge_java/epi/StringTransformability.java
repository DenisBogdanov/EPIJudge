package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class StringTransformability {

    @EpiTest(testDataFile = "string_transformability.tsv")
    public static int transformString(Set<String> dict, String s, String t) {
        Queue<String> q = new ArrayDeque<>();
        q.add(s);
        int result = 1;

        Set<String> seen = new HashSet<>();
        seen.add(s);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String polled = q.poll();
                char[] chars = polled.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char initialLetter = chars[j];
                    for (int k = 0; k < 26; k++) {
                        chars[j] = (char) ('a' + k);
                        String newStr = new String(chars);
                        if (newStr.equals(t)) return result;
                        if (dict.contains(newStr) && seen.add(newStr)) {
                            q.offer(newStr);
                        }
                    }

                    chars[j] = initialLetter;
                }
            }

            result++;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
