package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteKthLastFromList {

    @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")
    // Assumes L has at least k nodes, deletes the k-th last node in L.
    public static ListNode<Integer> removeKthLast(ListNode<Integer> head, int k) {
        var dummy = new ListNode<Integer>(-1, head);
        var runner = dummy;
        for (int i = 0; i < k + 1; i++) {
            runner = runner.next;
        }
        var toRemovePrev = dummy;
        while (runner != null) {
            toRemovePrev = toRemovePrev.next;
            runner = runner.next;
        }
        toRemovePrev.next = toRemovePrev.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
