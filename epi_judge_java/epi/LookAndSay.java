package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LookAndSay {

    @EpiTest(testDataFile = "look_and_say.tsv")
    public static String lookAndSay(int n) {
        String prev = "1";
        while (n-- > 1) {
            StringBuilder sb = new StringBuilder();
            int count = 1;

            for (int i = 1; i < prev.length(); i++) {
                if (prev.charAt(i) == prev.charAt(i - 1)) {
                    count++;
                } else {
                    sb.append(count).append(prev.charAt(i - 1));
                    count = 1;
                }
            }

            sb.append(count).append(prev.charAt(prev.length() - 1));
            prev = sb.toString();
        }

        return prev;
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
