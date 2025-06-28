package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeSieve {

    @EpiTest(testDataFile = "prime_sieve.tsv")
    // Given n, return all primes up to and including n.
    public static List<Integer> generatePrimes(int n) {
        var isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        for (int num = 2; num <= n; num++) {
            if (isPrime[num]) {
                for (int i = num * 2; i <= n; i += num) {
                    isPrime[i] = false;
                }
            }
        }

        var result = new ArrayList<Integer>();
        for (int num = 2; num <= n; num++) {
            if (isPrime[num]) {
                result.add(num);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
