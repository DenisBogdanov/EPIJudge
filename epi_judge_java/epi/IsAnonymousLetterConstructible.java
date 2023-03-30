package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsAnonymousLetterConstructible {

    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
    public static boolean isLetterConstructibleFromMagazine(String letterText, String magazineText) {
        int[] magazineTextDecomposition = decompose(magazineText);
        for (char letter : letterText.toCharArray()) {
            if (--magazineTextDecomposition[letter] < 0) return false;
        }

        return true;
    }

    private static int[] decompose(String s) {
        int[] result = new int[256];
        for (char letter : s.toCharArray()) {
            result[letter]++;
        }

        return result;
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
