package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> digits) {
    int carry = 1;

    for (int i = digits.size() - 1; i >= 0; i--) {
      if (digits.get(i) + carry <= 9) {
        digits.set(i, digits.get(i) + carry);
        carry = 0;
        break;
      }
      digits.set(i, 0);
    }

    if (carry == 1) {
      digits.add(0, 1);
    }

    return digits;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
