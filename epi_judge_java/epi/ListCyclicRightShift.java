package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ListCyclicRightShift {

  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")
  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> head, int k) {
    if (head == null) {
      return null;
    }

    ListNode<Integer> oldTail = head;

    int size = 1;
    while (oldTail.next != null) {
      size++;
      oldTail = oldTail.next;
    }

    k %= size;

    if (k == 0) {
      return head;
    }

    oldTail.next = head;

    int stepsToNewHead = size - k;

    ListNode<Integer> newTail = oldTail;
    while (stepsToNewHead-- > 0) {
      newTail = newTail.next;
    }

    ListNode<Integer> newHead = newTail.next;
    newTail.next = null;

    return newHead;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
