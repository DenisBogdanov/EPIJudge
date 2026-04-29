package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsAnonymousLetterConstructible {

    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
    public static boolean isLetterConstructibleFromMagazine(String letterText, String magazineText) {
        int[] counts = new int[256];
        for (int i = 0; i < magazineText.length(); i++) {
            counts[magazineText.charAt(i)]++;
        }
        for (int i = 0; i < letterText.length(); i++) {
            if (--counts[letterText.charAt(i)] < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
