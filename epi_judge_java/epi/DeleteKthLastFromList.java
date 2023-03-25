package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteKthLastFromList {

    @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")
    // Assumes L has at least k nodes, deletes the k-th last node in L.
    public static ListNode<Integer> removeKthLast(ListNode<Integer> list, int k) {
        ListNode<Integer> dummy = new ListNode<>(-1, list);

        ListNode<Integer> aheadRunner = list;
        for (int i = 0; i < k; i++) {
            aheadRunner = aheadRunner.next;
        }

        ListNode<Integer> runner = dummy;
        while (aheadRunner != null) {
            runner = runner.next;
            aheadRunner = aheadRunner.next;
        }

        runner.next = runner.next.next;
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
