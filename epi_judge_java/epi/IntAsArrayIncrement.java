package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IntAsArrayIncrement {

    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> list) {
        int carry = 1;
        for (int i = list.size() - 1; i >= 0; i--) {
            int sum = list.get(i) + carry;
            if (sum > 9) {
                sum %= 10;
                carry = 1;
                list.set(i, sum);
            } else {
                list.set(i, sum);
                return list;
            }
        }

        list.add(0, 1);

        return list;
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
