package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.List;

public class SortedArrayRemoveDups {

    // Returns the number of valid entries after deletion.
    public static int deleteDuplicates(List<Integer> list) {
        if (list.isEmpty()) return 0;
        int result = 1;

        for (int i = 1; i < list.size(); i++) {
            if (!list.get(i).equals(list.get(result - 1))) {
                list.set(result, list.get(i));
                result++;
            }
        }

        return result;
    }

    @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
    public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                        List<Integer> A)
            throws Exception {
        int end = executor.run(() -> deleteDuplicates(A));
        return A.subList(0, end);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
