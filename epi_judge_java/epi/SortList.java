package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortList {

    @EpiTest(testDataFile = "sort_list.tsv")
    public static ListNode<Integer> stableSortList(ListNode<Integer> head) {
        int size = 0;
        var runner = head;
        while (runner != null) {
            runner = runner.next;
            size++;
        }

        return sort(head, size);
    }

    private static ListNode<Integer> sort(ListNode<Integer> head, int size) {
        if (size <= 1) return head;

        int half = (size + 1) / 2;
        int count = 1;
        var runner = head;
        while (count < half) {
            runner = runner.next;
            count++;
        }

        var right = runner.next;
        runner.next = null;

        return SortedListsMerge.mergeTwoSortedLists(sort(head, half), sort(right, size - half));
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
