package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DirectoryPathNormalization {

    @EpiTest(testDataFile = "directory_path_normalization.tsv")
    public static String shortestEquivalentPath(String path) {
        boolean isAbsolute = path.startsWith("/");
        var elementsStack = new ArrayList<String>();

        Arrays.stream(path.split("/"))
                .filter(element -> !element.isBlank())
                .filter(element -> !".".equals(element))
                .forEach(element -> {
                    if ("..".equals(element)) {
                        if (elementsStack.isEmpty()) {
                            elementsStack.add(element);
                        } else if ("..".equals(elementsStack.getLast())) {
                            elementsStack.add(element);
                        } else {
                            elementsStack.removeLast();
                        }
                    } else {
                        elementsStack.add(element);
                    }
                });

        return elementsStack.stream()
                .collect(Collectors.joining("/", isAbsolute ? "/" : "", ""));
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
