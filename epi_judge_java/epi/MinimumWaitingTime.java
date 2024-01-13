package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MinimumWaitingTime {

    @EpiTest(testDataFile = "minimum_waiting_time.tsv")
    public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {
        serviceTimes.sort(null);
        int waitingTime = 0;
        int n = serviceTimes.size();
        for (int i = 0; i < n; i++) {
            waitingTime += serviceTimes.get(i) * (n - i - 1);
        }

        return waitingTime;
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
