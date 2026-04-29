package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RemoveDuplicatesFromSortedList {

    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")
    public static ListNode<Integer> removeDuplicates(ListNode<Integer> list) {
        var runner = list;
        while (runner != null && runner.next != null) {
            if (runner.data.equals(runner.next.data)) {
                runner.next = runner.next.next;
            } else {
                runner = runner.next;
            }
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
