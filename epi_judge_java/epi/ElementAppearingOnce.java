package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ElementAppearingOnce {

    @EpiTest(testDataFile = "element_appearing_once.tsv")
    public static int findElementAppearsOnce(List<Integer> nums) {
        Map<Integer, Integer> numToCountMap = new HashMap<>();
        for (int num : nums) {
            numToCountMap.merge(num, 1, Integer::sum);
        }
        for (var entry : numToCountMap.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return -1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ElementAppearingOnce.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
