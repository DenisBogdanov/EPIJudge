package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortList {

    @EpiTest(testDataFile = "sort_list.tsv")
    public static ListNode<Integer> stableSortList(ListNode<Integer> list) {
        if (list == null || list.next == null) return list;

        ListNode<Integer> preSLow = null;
        ListNode<Integer> slow = list;
        ListNode<Integer> fast = list;

        while (fast != null && fast.next != null) {
            preSLow = slow;
            fast = fast.next.next;
            slow = slow.next;
        }

        preSLow.next = null;

        return SortedListsMerge.mergeTwoSortedLists(stableSortList(list), stableSortList(slow));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortList.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
