package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FirstMissingPositiveEntry {

    @EpiTest(testDataFile = "first_missing_positive_entry.tsv")
    public static int findFirstMissingPositive(List<Integer> a) {
        Set<Integer> set = a.stream().filter(num -> num > 0).collect(Collectors.toSet());

        int candidate = 1;
        while (true) {
            if (!set.contains(candidate)) return candidate;
            candidate++;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "FirstMissingPositiveEntry.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
