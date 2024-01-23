package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SmallestNonconstructibleValue {

    @EpiTest(testDataFile = "smallest_nonconstructible_value.tsv")
    public static int smallestNonconstructibleValue(List<Integer> list) {
        list.sort(null);
        int currSum = 0;
        for (Integer num : list) {
            if (currSum + 1 < num) {
                return currSum + 1;
            }

            currSum += num;
        }

        return currSum + 1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SmallestNonconstructibleValue.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
