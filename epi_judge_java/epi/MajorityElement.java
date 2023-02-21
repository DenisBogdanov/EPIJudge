package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;

public class MajorityElement {

    public static String majoritySearch(Iterator<String> stream) {
        String candidate = "";
        int count = 0;

        while (stream.hasNext()) {
            String next = stream.next();
            if (next.equals(candidate)) {
                count++;
            } else {
                if (count > 1) {
                    count--;
                } else {
                    count = 1;
                    candidate = next;
                }
            }
        }

        return candidate;
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
