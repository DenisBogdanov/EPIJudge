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
        if (s.length() > 12) return new ArrayList<>();
        List<String> result = new ArrayList<>();

        int n = s.length();
        for (int i = 1; i <= n - 3; i++) {
            if (s.charAt(0) == '0' && i > 1) break;
            int a = Integer.parseInt(s.substring(0, i));
            if (a > 255) break;

            for (int j = i + 1; j <= n - 2; j++) {
                if (s.charAt(i) == '0' && j > i + 1) break;
                int b = Integer.parseInt(s.substring(i, j));
                if (b > 255) break;

                for (int k = j + 1; k <= n - 1; k++) {
                    if (s.charAt(j) == '0' && k > j + 1) break;
                    int c = Integer.parseInt(s.substring(j, k));
                    if (c > 255) break;

                    if (s.charAt(k) == '0' && n - k > 1) continue;
                    long d = Integer.parseInt(s.substring(k));
                    if (d > 255) continue;

                    result.add(String.format("%d.%d.%d.%d", a, b, c, d));
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
                        .runFromAnnotations(args, "ValidIpAddresses.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
