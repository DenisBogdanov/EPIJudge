package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.List;

public class AbsentValueArray {

    public static int findMissingElement(Iterable<Integer> stream) {
        int n = 0;
        long totalSum = 0;
        for (int num : stream) {
            n++;
            totalSum += num;
        }
        long expectedSum = (long) n * (n + 1) / 2;
        return (int) (expectedSum - totalSum);
    }

    @EpiTest(testDataFile = "absent_value_array.tsv")
    public static void findMissingElementWrapper(List<Integer> stream)
            throws Exception {
        try {
            int res = findMissingElement(stream);
            if (stream.stream().filter(a -> a.equals(res)).findFirst().isPresent()) {
                throw new TestFailure(String.valueOf(res) + " appears in stream");
            }
        } catch (IllegalArgumentException e) {
            throw new TestFailure("Unexpected no missing element exception");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "AbsentValueArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
