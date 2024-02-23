package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseSublist {

    @EpiTest(testDataFile = "reverse_sublist.tsv")
    public static ListNode<Integer> reverseSublist(ListNode<Integer> head, int start, int finish) {
        var dummy = new ListNode<>(-1, head);
        var sublistHead = dummy;

        int k = 1;
        while (k++ < start) {
            sublistHead = sublistHead.next;
        }

        var runner = sublistHead.next;

        while (start++ < finish) {
            var next = runner.next;
            runner.next = next.next;
            next.next = sublistHead.next;
            sublistHead.next = next;
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
