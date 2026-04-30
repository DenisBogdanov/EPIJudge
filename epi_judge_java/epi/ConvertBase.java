package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {

    @EpiTest(testDataFile = "convert_base.tsv")
    public static String convertBase(String numAsString, int b1, int b2) {
        int num = Integer.parseInt(numAsString, b1);
        return Integer.toString(num, b2).toUpperCase();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
