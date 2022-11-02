package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseSublist {

    @EpiTest(testDataFile = "reverse_sublist.tsv")
    public static ListNode<Integer> reverseSublist(ListNode<Integer> head, int start, int finish) {
        if (start == finish) return head;

        ListNode<Integer> dummy = new ListNode<>(-1, head);
        ListNode<Integer> runner = dummy;

        for (int i = 1; i < start; i++) {
            runner = runner.next;
        }

        ListNode<Integer> prev = runner;
        runner = runner.next;

        for (int i = start; i < finish; i++) {
            ListNode<Integer> next = runner.next;
            runner.next = next.next;
            next.next = runner;
            prev.next = next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
