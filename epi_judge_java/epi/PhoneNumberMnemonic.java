package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneNumberMnemonic {
    private static final String[] MAPPING = {"0", "1", "ABC", "DEF", "GHI",
            "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
    public static List<String> phoneMnemonic(String phoneNumber) {
        List<String> result = new ArrayList<>();

        recur(phoneNumber, 0, new StringBuilder(), result);

        return result;
    }

    private static void recur(String phoneNumber, int index, StringBuilder sb, List<String> result) {
        if (sb.length() == phoneNumber.length()) {
            result.add(sb.toString());
        } else {
            int mappingIndex = phoneNumber.charAt(index) - '0';
            for (char c : MAPPING[mappingIndex].toCharArray()) {
                sb.append(c);
                recur(phoneNumber, index + 1, sb, result);
                sb.setLength(sb.length() - 1);
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
