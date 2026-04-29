package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchFirstKey {

    @EpiTest(testDataFile = "search_first_key.tsv")
    public static int searchFirstOfK(List<Integer> sortedNums, int k) {
        if (sortedNums.isEmpty() || sortedNums.get(sortedNums.size() - 1) < k) return -1;
        int left = -1;
        int right = sortedNums.size();
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (sortedNums.get(mid) >= k) right = mid;
            else left = mid;
        }
        return (sortedNums.get(right) == k ? right : -1);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
