package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LookAndSay {

    @EpiTest(testDataFile = "look_and_say.tsv")
    public static String lookAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            char prev = s.charAt(0);
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) != prev) {
                    sb.append(count).append(prev);
                    count = 1;
                    prev = s.charAt(j);
                } else {
                    count++;
                }
            }
            sb.append(count).append(prev);
            s = sb.toString();
        }
        return s;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
