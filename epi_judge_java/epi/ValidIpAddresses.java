package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidIpAddresses {

    @EpiTest(testDataFile = "valid_ip_addresses.tsv")
    public static List<String> getValidIpAddress(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() > 12 || s.length() < 4) return result;

        recur(s, 0, new ArrayList<>(), result);
        return result;
    }

    private static void recur(String s, int start, List<String> parts, List<String> result) {
        if (parts.size() == 4) {
            if (start != s.length()) return;
            result.add(String.join(".", parts));
            return;
        }

        for (int i = start; i <= start + 3; i++) {
            if (i >= s.length()) break;

            String candidate = s.substring(start, i + 1);
            if (Integer.parseInt(candidate) > 255 || (candidate.length() > 1 && candidate.charAt(0) == '0')) {
                break;
            }

            parts.add(candidate);
            recur(s, i + 1, parts, result);

            parts.remove(parts.size() - 1);
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
                        .runFromAnnotations(args, "ValidIpAddresses.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
