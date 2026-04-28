package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class DirectoryPathNormalization {

    @EpiTest(testDataFile = "directory_path_normalization.tsv")
    public static String shortestEquivalentPath(String path) {
        if (path.isEmpty()) return path;
        boolean isAbs = path.charAt(0) == '/';

        var parts = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String part : parts) {
            if (part.equals(".") || part.isEmpty()) {
                // NOP
            } else if (part.equals("..")) {
                if (!stack.isEmpty() && !stack.peek().equals("..")) {
                    stack.pop();
                } else if (!isAbs) {
                    stack.push("..");
                }
            } else {
                stack.push(part);
            }
        }

        StringBuilder ans = new StringBuilder();
        if (isAbs) ans.append('/');
        while (!stack.isEmpty()) {
            ans.append(stack.pollLast());
            ans.append('/');
        }
        if (!ans.isEmpty() && ans.length() > 1) {
            ans.setLength(ans.length() - 1);
        }
        return ans.toString();
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
