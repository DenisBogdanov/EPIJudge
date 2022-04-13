package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsListPalindromic {

  @EpiTest(testDataFile = "is_list_palindromic.tsv")
  public static boolean isLinkedListAPalindrome(ListNode<Integer> head) {
    ListNode<Integer> slow = head;
    ListNode<Integer> fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    ListNode<Integer> tail = reverse(slow);

    while (head != null && tail != null) {
      if (!head.data.equals(tail.data)) {
        return false;
      }
      head = head.next;
      tail = tail.next;
    }

    return true;
  }

  private static ListNode<Integer> reverse(ListNode<Integer> head) {
    ListNode<Integer> prev = null;
    ListNode<Integer> runner = head;

    while (runner != null) {
      ListNode<Integer> temp = runner.next;
      runner.next = prev;
      prev = runner;
      runner = temp;
    }

    return prev;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
