package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchForMinMaxInArray {

    @EpiTest(testDataFile = "search_for_min_max_in_array.tsv")
    public static MinMax findMinMax(List<Integer> list) {
        int min = list.get(0);
        int max = min;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= min) {
                min = list.get(i);
            } else if (list.get(i) > max) {
                max = list.get(i);
            }
        }

        return new MinMax(min, max);
    }

    @EpiUserType(ctorParams = {Integer.class, Integer.class})
    public static class MinMax {
        public Integer smallest;
        public Integer largest;

        public MinMax(Integer smallest, Integer largest) {
            this.smallest = smallest;
            this.largest = largest;
        }

        private static MinMax minMax(Integer a, Integer b) {
            return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            MinMax minMax = (MinMax) o;

            if (!smallest.equals(minMax.smallest)) {
                return false;
            }
            return largest.equals(minMax.largest);
        }

        @Override
        public String toString() {
            return "min: " + smallest + ", max: " + largest;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchForMinMaxInArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
