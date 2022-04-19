package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class FindSalaryThreshold {

  @EpiTest(testDataFile = "find_salary_threshold.tsv")
  public static double findSalaryCap(int targetPayroll, List<Integer> currentSalaries) {
    Collections.sort(currentSalaries);

    double currentSum = 0.0;

    for (int i = 0; i < currentSalaries.size(); i++) {
      Integer currentSalary = currentSalaries.get(i);
      double potentialCap = (targetPayroll - currentSum) / (currentSalaries.size() - i);
      if (currentSalary > potentialCap) {
        return potentialCap;
      }

      currentSum += currentSalary;
    }

    return currentSum < targetPayroll ? -1 : currentSalaries.get(currentSalaries.size() - 1);
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
