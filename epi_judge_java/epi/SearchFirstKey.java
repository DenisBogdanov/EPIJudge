package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchFirstKey {

    @EpiTest(testDataFile = "search_first_key.tsv")
    public static int searchFirstOfK(List<Integer> list, int k) {
        int left = -1;
        int right = list.size();

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid) < k) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return right < list.size() && list.get(right) == k ? right : -1;
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
