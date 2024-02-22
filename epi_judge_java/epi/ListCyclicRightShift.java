package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ListCyclicRightShift {

    @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")
    public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> head, int k) {
        if (head == null) return null;

        int len = 0;
        var runner = head;
        var last = runner;

        while (runner != null) {
            if (runner.next == null) {
                last = runner;
            }

            runner = runner.next;
            len++;
        }

        k %= len;
        if (k == 0) return head;

        int shift = len - k;
        runner = head;
        for (int i = 1; i < shift; i++) {
            runner = runner.next;
        }

        var newHead = runner.next;
        runner.next = null;
        last.next = head;

        return newHead;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
