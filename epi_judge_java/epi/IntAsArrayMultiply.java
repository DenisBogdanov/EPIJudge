package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntAsArrayMultiply {

    @EpiTest(testDataFile = "int_as_array_multiply.tsv")
    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
        if (num1.size() == 1 && num1.get(0) == 0) return List.of(0);
        if (num2.size() == 1 && num2.get(0) == 0) return List.of(0);

        List<Integer> result = new ArrayList<>();
        boolean isNegative = num1.get(0) * num2.get(0) < 0;
        num1.set(0, Math.abs(num1.get(0)));
        num2.set(0, Math.abs(num2.get(0)));

        for (int i = num2.size() - 1; i >= 0; i--) {
            int b = num2.get(i);
            int carry = 0;

            int index = num2.size() - i - 1;

            for (int j = num1.size() - 1; j >= 0; j--) {
                int a = num1.get(j);

                int mult = a * b;
                if (index == result.size()) {
                    result.add(0);
                }

                Integer prev = result.get(index);
                int sum = prev + mult + carry;
                carry = sum / 10;
                sum %= 10;
                result.set(index, sum);

                index++;
            }

            if (carry != 0) {
                result.add(carry);
            }
        }

        Collections.reverse(result);
        if (isNegative) result.set(0, result.get(0) * -1);

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
