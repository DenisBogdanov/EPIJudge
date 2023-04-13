package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class FindSalaryThreshold {

    @EpiTest(testDataFile = "find_salary_threshold.tsv")
    public static double findSalaryCap(int targetPayroll, List<Integer> currentSalaries) {
        int maxSalary = 0;
        int currPayroll = 0;
        for (Integer currentSalary : currentSalaries) {
            maxSalary = Math.max(maxSalary, currentSalary);
            currPayroll += currentSalary;
        }

        if (currPayroll < targetPayroll) return -1;

        double left = 0.0;
        double right = maxSalary;

        while (left + 1e-6 < right) {
            double candidate = left + (right - left) / 2;

            if (satisfies(targetPayroll, currentSalaries, candidate)) {
                left = candidate;
            } else {
                right = candidate;
            }
        }

        return left;
    }

    private static boolean satisfies(int targetPayroll, List<Integer> currentSalaries, double candidate) {
        double currPayroll = 0;
        for (Integer currentSalary : currentSalaries) {
            currPayroll += Math.min(currentSalary, candidate);

            if (currPayroll > targetPayroll) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "FindSalaryThreshold.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
