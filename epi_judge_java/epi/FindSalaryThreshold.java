package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Comparator;
import java.util.List;

public class FindSalaryThreshold {

    @EpiTest(testDataFile = "find_salary_threshold.tsv")
    public static double findSalaryCap(int targetPayroll, List<Integer> currentSalaries) {
        int totalSum = 0;
        for (Integer currentSalary : currentSalaries) {
            totalSum += currentSalary;
        }

        if (totalSum < targetPayroll) return -1;

        Integer max = currentSalaries.stream().max(Comparator.comparingInt(Integer::intValue)).orElseThrow();
        double left = 0.0;
        double right = max;

        while (right - left > 10e-7) {
            double candidate = (left + right) / 2;
            if (ok(candidate, currentSalaries, targetPayroll)) {
                left = candidate;
            } else {
                right = candidate;
            }
        }

        return left;
    }

    private static boolean ok(double candidate, List<Integer> currentSalaries, int targetPayroll) {
        double sum = 0;
        for (Integer currentSalary : currentSalaries) {
            sum += Math.min(currentSalary, candidate);
            if (sum > targetPayroll) {
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
