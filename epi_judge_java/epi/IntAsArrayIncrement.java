package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IntAsArrayIncrement {
    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> num) {
        int carry = 1;
        for (int i = num.size() - 1; i >= 0; i--) {
            int sum = num.get(i) + carry;
            num.set(i, sum % 10);
            carry = sum / 10;
            if (carry == 0) return num;
        }
        num.add(0, 1);
        return num;
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
