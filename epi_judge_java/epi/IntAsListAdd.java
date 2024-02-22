package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntAsListAdd {

    @EpiTest(testDataFile = "int_as_list_add.tsv")
    public static ListNode<Integer> addTwoNumbers(ListNode<Integer> num1, ListNode<Integer> num2) {
        var sentinel = new ListNode<>(-1, null);
        var runner = sentinel;

        int carry = 0;
        while (num1 != null || num2 != null) {
            int sum = 0;
            if (num1 != null) {
                sum += num1.data;
                num1 = num1.next;
            }

            if (num2 != null) {
                sum += num2.data;
                num2 = num2.next;
            }

            sum += carry;
            carry = sum / 10;
            sum %= 10;

            runner.next = new ListNode<>(sum, null);
            runner = runner.next;
        }

        if (carry == 1) {
            runner.next = new ListNode<>(1, null);
        }

        return sentinel.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
