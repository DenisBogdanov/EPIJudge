package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class MakingChange {
  private static final int[] COINS = {100, 50, 25, 10, 5, 1};

  @EpiTest(testDataFile = "making_change.tsv")
  public static int changeMaking(int cents) {
    int result = 0;

    for (int coin : COINS) {
      result += cents / coin;
      cents %= coin;
    }

    return result;
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
