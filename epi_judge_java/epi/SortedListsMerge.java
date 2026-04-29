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
                runner.next = list1;
                list1 = list1.next;
            } else {
                runner.next = list2;
                list2 = list2.next;
            }
            runner = runner.next;
        }
        if (list1 == null) runner.next = list2;
        if (list2 == null) runner.next = list1;
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
