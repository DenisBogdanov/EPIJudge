package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class MajorityElement {

  public static String majoritySearch(Iterator<String> stream) {
    int count = 1;
    String current = stream.next();

    while (stream.hasNext()) {
      String next = stream.next();
      if (Objects.equals(next, current)) {
        count++;
      } else {
        if (count < 1) {
          current = next;
          count = 1;
        } else {
          count--;
        }
      }
    }

    return current;
  }

  @EpiTest(testDataFile = "majority_element.tsv")
  public static String majoritySearchWrapper(List<String> stream) {
    return majoritySearch(stream.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MajorityElement.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
