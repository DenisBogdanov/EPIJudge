package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;

public class StringTransformability {

    @EpiTest(testDataFile = "string_transformability.tsv")
    public static int transformString(Set<String> dict, String s, String t) {
        if (s.equals(t)) return 0;
        if (!dict.contains(s) || !dict.contains(t)) return -1;

        Queue<String> q = new ArrayDeque<>();
        q.offer(s);
        int distance = 1;
        dict.remove(s);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String polled = q.poll();
                char[] polledChars = polled.toCharArray();

                for (int j = 0; j < polledChars.length; j++) {
                    char prev = polledChars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        polledChars[j] = c;
                        String candidate = new String(polledChars);
                        if (dict.contains(candidate)) {
                            if (t.equals(candidate)) return distance;
                            q.offer(candidate);
                            dict.remove(candidate);
                        }
                    }

                    polledChars[j] = prev;
                }
            }

            distance++;
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
