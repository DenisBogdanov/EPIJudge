package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntAsListAdd {

    @EpiTest(testDataFile = "int_as_list_add.tsv")
    public static ListNode<Integer> addTwoNumbers(ListNode<Integer> list1, ListNode<Integer> list2) {
        var dummy = new ListNode<>(-1, null);
        var runner = dummy;
        int carry = 0;

        while (list1 != null || list2 != null) {
            int sum = 0;
            if (list1 != null) {
                sum += list1.data;
                list1 = list1.next;
            }

            if (list2 != null) {
                sum += list2.data;
                list2 = list2.next;
            }

            sum += carry;
            carry = 0;
            if (sum > 9) {
                carry = 1;
                sum %= 10;
            }

            runner.next = new ListNode<>(sum, null);
            runner = runner.next;
        }

        if (carry == 1) {
            runner.next = new ListNode<>(1, null);
        }

        return dummy.next;
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
