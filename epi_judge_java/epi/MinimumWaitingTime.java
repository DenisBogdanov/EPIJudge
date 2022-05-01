package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class MinimumWaitingTime {

  @EpiTest(testDataFile = "minimum_waiting_time.tsv")
  public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {
    Collections.sort(serviceTimes);
    int result = 0;

    for (int i = 0; i < serviceTimes.size() - 1; i++) {
      result += serviceTimes.get(i) * (serviceTimes.size() - i - 1);
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumWaitingTime.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
