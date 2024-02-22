package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RemoveDuplicatesFromSortedList {

    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")
    public static ListNode<Integer> removeDuplicates(ListNode<Integer> head) {
        if (head == null) return null;

        var curr = head;
        var next = head.next;

        while (next != null) {
            if (!curr.data.equals(next.data)) {
                curr.next = next;
                curr = curr.next;
            }

            next = next.next;
        }

        curr.next = null;

        return head;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
