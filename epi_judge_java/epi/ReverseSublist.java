package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseSublist {

  @EpiTest(testDataFile = "reverse_sublist.tsv")
  public static ListNode<Integer> reverseSublist(ListNode<Integer> head, int start, int finish) {
    if (start == finish) {
      return head;
    }

    ListNode<Integer> dummy = new ListNode<>(0, head);
    ListNode<Integer> prev = dummy;
    int i = 1;

    while (i++ < start) {
      prev = prev.next;
    }

    ListNode<Integer> runner = prev.next;
    while (start++ < finish) {
      ListNode<Integer> temp = runner.next;
      runner.next = temp.next;
      temp.next = prev.next;
      prev.next = temp;
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
