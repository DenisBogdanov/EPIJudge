package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntAsListAdd {

  @EpiTest(testDataFile = "int_as_list_add.tsv")
  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> head1,
                                                ListNode<Integer> head2) {

    int carry = 0;
    ListNode<Integer> resultDummyHead = new ListNode<>(0, null);
    ListNode<Integer> runner = resultDummyHead;

    while (head1 != null || head2 != null) {
      int sum = carry;

      if (head1 != null) {
        sum += head1.data;
        head1 = head1.next;
      }

      if (head2 != null) {
        sum += head2.data;
        head2 = head2.next;
      }

      carry = sum / 10;
      runner.next = new ListNode<>(sum % 10, null);
      runner = runner.next;
    }

    if (carry > 0) {
      runner.next = new ListNode<>(carry, null);
    }

    return resultDummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
