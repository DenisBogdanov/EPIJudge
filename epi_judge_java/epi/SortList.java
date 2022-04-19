package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortList {

  @EpiTest(testDataFile = "sort_list.tsv")
  public static ListNode<Integer> stableSortList(ListNode<Integer> head) {
//    return insertionSort(head);


    if (head == null || head.next == null) {
      return head;
    }

    ListNode<Integer> preSlow = null;
    ListNode<Integer> slow = head;
    ListNode<Integer> fast = head;

    while (fast != null && fast.next != null) {
      preSlow = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    preSlow.next = null;  // splits the list into two equal-sized lists

    return SortedListsMerge.mergeTwoSortedLists(stableSortList(head), stableSortList(slow));
  }

  private static ListNode<Integer> insertionSort(ListNode<Integer> head) {
    ListNode<Integer> dummy = new ListNode<>(0, head);
    ListNode<Integer> current = head;

    while (current != null && current.next != null) {
      if (current.data > current.next.data) {
        ListNode<Integer> target = current.next;
        ListNode<Integer> pre = dummy;

        while (pre.next.data < target.data) {
          pre = pre.next;
        }

        ListNode<Integer> temp = pre.next;
        pre.next = target;
        current.next = target.next;
        target.next = temp;
      } else {
        current = current.next;
      }
    }

    return dummy.next;
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
