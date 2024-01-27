package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntAsArrayIncrement {

    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> list) {
        int carry = 1;
        List<Integer> result = new ArrayList<>();

        for (int i = list.size() - 1; i >= 0; i--) {
            int sum = list.get(i) + carry;
            if (sum > 9) {
                sum = 0;
                carry = 1;
            } else {
                carry = 0;
            }

            result.add(sum);
        }

        if (carry != 0) result.add(carry);
        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
