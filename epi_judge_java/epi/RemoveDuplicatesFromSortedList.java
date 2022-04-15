package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RemoveDuplicatesFromSortedList {

  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")
  public static ListNode<Integer> removeDuplicates(ListNode<Integer> head) {
    ListNode<Integer> runner = head;

    while (runner != null) {
      ListNode<Integer> nextDistinct = runner.next;

      while (nextDistinct != null && nextDistinct.data.equals(runner.data)) {
        nextDistinct = nextDistinct.next;
      }

      runner.next = nextDistinct;
      runner = runner.next;
    }

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
