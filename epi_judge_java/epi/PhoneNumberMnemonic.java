package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneNumberMnemonic {
  private static final String[] MAPPING = {
      "0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
  public static List<String> phoneMnemonic(String phoneNumber) {
    char[] partialMnemonic = new char[phoneNumber.length()];
    List<String> result = new ArrayList<>();
    helper(phoneNumber, 0, partialMnemonic, result);
    return result;
  }

  private static void helper(String phoneNumber, int index, char[] partialMnemonic, List<String> result) {
    if (index == phoneNumber.length()) {
      result.add(new String(partialMnemonic));
    } else {
      for (int i = 0; i < MAPPING[phoneNumber.charAt(index) - '0'].length(); i++) {
        char c = MAPPING[phoneNumber.charAt(index) - '0'].charAt(i);
        partialMnemonic[index] = c;
        helper(phoneNumber, index + 1, partialMnemonic, result);
      }
    }
  }

  @EpiTestComparator
  public static boolean comp(List<String> expected, List<String> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
