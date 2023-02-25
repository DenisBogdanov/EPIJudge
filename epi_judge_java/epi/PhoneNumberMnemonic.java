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
        return helper(phoneNumber, 0);
    }

    private static List<String> helper(String phoneNumber, int index) {
        List<String> result = new ArrayList<>();

        if (index == phoneNumber.length()) return result;

        for (char c : MAPPING[phoneNumber.charAt(index) - '0'].toCharArray()) {
            String s = "";
            s += c;

            List<String> suffixes = helper(phoneNumber, index + 1);
            if (suffixes.isEmpty()) {
                result.add(s);
            } else {
                for (String suffix : suffixes) {
                    result.add(s + suffix);
                }
            }
        }

        return result;
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
