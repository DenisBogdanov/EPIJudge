package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class EvenOddListMerge {

  @EpiTest(testDataFile = "even_odd_list_merge.tsv")
  public static ListNode<Integer> evenOddMerge(ListNode<Integer> head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode<Integer> evenDummyHead = new ListNode<>(0, null);
    ListNode<Integer> oddDummyHead = new ListNode<>(0, null);

    List<ListNode<Integer>> tails = Arrays.asList(evenDummyHead, oddDummyHead);

    int turn = 0;

    for (ListNode<Integer> runner = head; runner != null; runner = runner.next) {
      tails.get(turn).next = runner;
      tails.set(turn, runner);
      turn ^= 1;
    }

    tails.get(1).next = null;
    tails.get(0).next = oddDummyHead.next;

    return evenDummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
