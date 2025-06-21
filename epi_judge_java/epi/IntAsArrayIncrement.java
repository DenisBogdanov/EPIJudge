package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IntAsArrayIncrement {

    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> list) {
        int carry = 1;
        int index = list.size() - 1;
        while (index >= 0) {
            if (list.get(index) == 9) {
                list.set(index, 0);
            } else {
                list.set(index, list.get(index) + 1);
                carry = 0;
                break;
            }
            index--;
        }

        if (carry == 1) {
            list.addFirst(1);
        }

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
