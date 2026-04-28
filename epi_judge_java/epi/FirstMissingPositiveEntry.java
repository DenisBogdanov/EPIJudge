package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class FirstMissingPositiveEntry {

    @EpiTest(testDataFile = "first_missing_positive_entry.tsv")
    public static int findFirstMissingPositive(List<Integer> nums) {
        nums.sort(null);
        int i = 1;
        for (int j = 0; j < nums.size(); j++) {
            int num = nums.get(j);
            if (j > 0 && num == nums.get(j - 1)) continue;
            if (num > 0) {
                if (num != i) return i;
                i++;
            }
        }
        return i;
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
