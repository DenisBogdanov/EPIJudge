package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int num) {
    boolean isNegative = num < 0;

    char[] numChars = (isNegative
        ? String.valueOf(num).substring(1)
        : String.valueOf(num))
        .toCharArray();

    for (int i = 0; i < numChars.length / 2; i++) {
      char temp = numChars[i];
      numChars[i] = numChars[numChars.length - 1 - i];
      numChars[numChars.length - 1 - i] = temp;
    }

    return Long.parseLong(String.valueOf(numChars)) * (isNegative ? -1 : 1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
