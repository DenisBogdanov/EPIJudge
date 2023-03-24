package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedListsMerge {

    @EpiTest(testDataFile = "sorted_lists_merge.tsv")
    public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> list1, ListNode<Integer> list2) {
        ListNode<Integer> dummy = new ListNode<>(-1, null);
        ListNode<Integer> runner = dummy;

        while (list1 != null && list2 != null) {
            if (list1.data < list2.data) {
                runner.next = new ListNode<>(list1.data, null);
                list1 = list1.next;
            } else {
                runner.next = new ListNode<>(list2.data, null);
                list2 = list2.next;
            }

            runner = runner.next;
        }

        if (list1 != null) {
            runner.next = list1;
        } else {
            runner.next = list2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
