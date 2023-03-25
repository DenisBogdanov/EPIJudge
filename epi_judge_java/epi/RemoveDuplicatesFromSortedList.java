package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RemoveDuplicatesFromSortedList {

    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")
    public static ListNode<Integer> removeDuplicates(ListNode<Integer> list) {
        if (list == null || list.next == null) return list;

        ListNode<Integer> runner = list;

        while (runner != null) {
            while (runner.next != null && runner.data.equals(runner.next.data)) {
                runner.next = runner.next.next;
            }

            runner = runner.next;
        }

        return list;
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
