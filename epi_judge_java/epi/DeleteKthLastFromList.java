package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteKthLastFromList {

    @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")
    // Assumes L has at least k nodes, deletes the k-th last node in L.
    public static ListNode<Integer> removeKthLast(ListNode<Integer> head, int k) {
        var fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        if (fast == null) {
            return head.next;
        }

        fast = fast.next;

        var slow = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
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
