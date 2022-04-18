package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedListsMerge {

  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> head1,
                                                      ListNode<Integer> head2) {

    ListNode<Integer> dummy = new ListNode<>(0, null);
    ListNode<Integer> runner = dummy;

    while (head1 != null && head2 != null) {
      if (head1.data < +head2.data) {
        runner.next = head1;
        head1 = head1.next;
      } else {
        runner.next = head2;
        head2 = head2.next;
      }

      runner = runner.next;
    }

    runner.next = head1 == null ? head2 : head1;

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
