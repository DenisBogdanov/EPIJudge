package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class DirectoryPathNormalization {

    @EpiTest(testDataFile = "directory_path_normalization.tsv")
    public static String shortestEquivalentPath(String path) {
        boolean isAbsolute = path.startsWith("/");
        Deque<String> partsDeque = new ArrayDeque<>();

        for (String part : path.split("/")) {
            if (part.isEmpty()) continue;

            switch (part) {
                case "..":
                    if (!isAbsolute && (partsDeque.isEmpty() || partsDeque.peek().equals(".."))) {
                        partsDeque.push(part);
                    } else {
                        partsDeque.pop();
                    }
                    break;
                case ".":
                    break;
                default:
                    partsDeque.push(part);
                    break;
            }
        }

        ArrayList<String> parts = new ArrayList<>(partsDeque);
        if (!isAbsolute && parts.isEmpty()) parts.add(".");
        Collections.reverse(parts);
        return (isAbsolute ? "/" : "") + String.join("/", parts);
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
