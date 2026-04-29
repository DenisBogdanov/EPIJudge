package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseSublist {

    @EpiTest(testDataFile = "reverse_sublist.tsv")
    public static ListNode<Integer> reverseSublist(ListNode<Integer> list, int start, int finish) {
        if (start == finish) return list;
        ListNode<Integer> dummy = new ListNode<>(-1, list);
        ListNode<Integer> beforeStartNode = dummy;
        for (int i = 1; i < start; i++) {
            beforeStartNode = beforeStartNode.next;
        }
        var runner = beforeStartNode.next;
        for (int i = start; i < finish; i++) {
            var temp = beforeStartNode.next;
            beforeStartNode.next = runner.next;
            runner.next = runner.next.next;
            beforeStartNode.next.next = temp;
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
