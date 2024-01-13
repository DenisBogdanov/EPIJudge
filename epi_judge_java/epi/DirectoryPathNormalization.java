package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class DirectoryPathNormalization {

    @EpiTest(testDataFile = "directory_path_normalization.tsv")
    public static String shortestEquivalentPath(String path) {
        boolean isAbsolutePath = path.startsWith("/");

        Deque<String> deque = new ArrayDeque<>();
        String[] parts = path.split("/");

        int depth = 0;
        int currDepth = 0;

        for (String part : parts) {
            if (part.isEmpty()) continue;

            switch (part) {
                case ".":
                    break;
                case "..":
                    if (!deque.isEmpty()) {
                        deque.pop();
                    }

                    currDepth++;
                    depth = Math.max(depth, currDepth);
                    break;
                default:
                    deque.push(part);
                    currDepth--;
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (isAbsolutePath) sb.append("/");
        else if (depth > 0) sb.append("../".repeat(depth));

        while (!deque.isEmpty()) {
            sb.append(deque.pollLast()).append("/");
        }

        if (sb.length() > 1) sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
