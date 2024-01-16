package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LookAndSay {

    @EpiTest(testDataFile = "look_and_say.tsv")
    public static String lookAndSay(int n) {
        String curr = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            char digit = curr.charAt(0);
            int count = 1;
            for (int j = 1; j < curr.length(); j++) {
                if (curr.charAt(j) == digit) {
                    count++;
                } else {
                    sb.append(count).append(digit);
                    count = 1;
                    digit = curr.charAt(j);
                }
            }

            sb.append(count).append(digit);

            curr = sb.toString();
        }

        return curr;
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
