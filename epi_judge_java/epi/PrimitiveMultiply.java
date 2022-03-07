package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    long result = 0;

    while (x != 0) {
      if ((x & 1) != 0) {
        // you need to create add function
        result = add(result, y);
      }

      x >>>= 1;
      y <<= 1;
    }

    return result;
  }

  private static long add(long a, long b) {
    long sum = 0;
    long carry = 0;
    long k = 1;
    long tempA = a;
    long tempB = b;

    while (tempA != 0 || tempB != 0) {
      long ak = a & k, bk = b & k;
      long carryout = (ak & bk) | (ak & carry) | (bk & carry);
      sum |= (ak ^ bk ^ carry);
      carry = carryout << 1;
      k <<= 1;
      tempA >>>= 1;
      tempB >>>= 1;
    }
    return sum | carry;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
