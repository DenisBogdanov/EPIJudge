package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;

public class MajorityElement {

    public static String majoritySearch(Iterator<String> stream) {
        int count = 0;
        String result = stream.next();

        while (stream.hasNext()) {
            String next = stream.next();
            if (next.equals(result)) {
                count++;
            } else {
                count--;
                if (count < 0) {
                    result = next;
                    count = 0;
                }
            }
        }

        return result;
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
