package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SunsetView {

    public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> heightsStack = new ArrayDeque<>();
        Deque<Integer> indicesStack = new ArrayDeque<>();
        int i = 0;
        while (sequence.hasNext()) {
            var next = sequence.next();
            while (!heightsStack.isEmpty() && heightsStack.peek() <= next) {
                heightsStack.pop();
                indicesStack.pop();
            }
            heightsStack.push(next);
            indicesStack.push(i);
            i++;
        }
        while (!indicesStack.isEmpty()) {
            ans.add(indicesStack.pop());
        }
        return ans;
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    public static List<Integer> examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
        return examineBuildingsWithSunset(sequence.iterator());
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SunsetView.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
