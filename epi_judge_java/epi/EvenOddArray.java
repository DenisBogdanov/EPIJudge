package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ToDo: Is there an optimal solution for keeping the order of odd and even elements?
 */
public class EvenOddArray {

    public static void evenOdd(List<Integer> list) {
        int evenIndex = 0;
        int oddIndex = list.size() - 1;

        while (evenIndex < oddIndex) {
            while (evenIndex < oddIndex && list.get(evenIndex) % 2 == 0) {
                evenIndex++;
            }
            while (evenIndex < oddIndex && list.get(oddIndex) % 2 != 0) {
                oddIndex--;
            }
            int temp = list.get(evenIndex);
            list.set(evenIndex, list.get(oddIndex));
            list.set(oddIndex, temp);
            evenIndex++;
            oddIndex--;
        }
    }

    @EpiTest(testDataFile = "even_odd_array.tsv")
    public static void evenOddWrapper(TimedExecutor executor, List<Integer> A)
            throws Exception {
        List<Integer> before = new ArrayList<>(A);
        executor.run(() -> evenOdd(A));

        boolean inOdd = false;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) % 2 == 0) {
                if (inOdd) {
                    throw new TestFailure("Even elements appear in odd part");
                }
            } else {
                inOdd = true;
            }
        }
        List<Integer> after = new ArrayList<>(A);
        Collections.sort(before);
        Collections.sort(after);
        if (!before.equals(after)) {
            throw new TestFailure("Elements mismatch");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvenOddArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
