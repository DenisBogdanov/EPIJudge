package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteKthLastFromList {

  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")
  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> head, int k) {
    ListNode<Integer> dummy = new ListNode<>(0, head);
    ListNode<Integer> first = head;

    while (k-- > 0) {
      first = first.next;
    }

    ListNode<Integer> second = dummy;
    while (first != null) {
      second = second.next;
      first = first.next;
    }

    second.next = second.next.next;
    return dummy.next;
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
