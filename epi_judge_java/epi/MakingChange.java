package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class MakingChange {

    @EpiTest(testDataFile = "making_change.tsv")
    public static int changeMaking(int cents) {
        int[] coins = {100, 50, 25, 10, 5, 1};
        int ans = 0;
        for (int coin : coins) {
            ans += cents / coin;
            cents %= coin;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MakingChange.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
