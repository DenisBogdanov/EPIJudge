package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ListCyclicRightShift {

    @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")
    public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> list, int k) {
        if (list == null || list.next == null) return list;

        int length = 1;
        ListNode<Integer> runner = list;
        while (runner.next != null) {
            runner = runner.next;
            length++;
        }

        ListNode<Integer> tailNode = runner;

        k %= length;
        if (k == 0) return list;

        int distFromStart = length - k - 1;
        runner = list;
        while (distFromStart-- > 0) {
            runner = runner.next;
        }

        ListNode<Integer> newStart = runner.next;
        runner.next = null;
        tailNode.next = list;

        return newStart;
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
