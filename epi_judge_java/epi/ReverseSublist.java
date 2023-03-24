package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseSublist {

    @EpiTest(testDataFile = "reverse_sublist.tsv")
    public static ListNode<Integer> reverseSublist(ListNode<Integer> list, int start, int finish) {
        if (start == finish) return list;

        ListNode<Integer> dummyNode = new ListNode<>(-1, list);
        int index = 1;

        ListNode<Integer> runner = dummyNode;

        while (index < start) {
            runner = runner.next;
            index++;
        }

        ListNode<Integer> prevStartNode = runner;

        runner = prevStartNode.next;

        while (start < finish) {
            ListNode<Integer> temp = runner.next;
            runner.next = runner.next.next;
            temp.next = prevStartNode.next;
            prevStartNode.next = temp;

            start++;
        }

        return dummyNode.next;
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
