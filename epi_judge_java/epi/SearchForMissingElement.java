package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchForMissingElement {

    @EpiTest(testDataFile = "find_missing_and_duplicate.tsv")
    public static DuplicateAndMissing findDuplicateMissing(List<Integer> nums) {
        int xor = 0;

        int n = nums.size();
        for (int i = 0; i < n; i++) {
            xor ^= i;
            xor ^= nums.get(i);
        }

        int differentBit = xor & -xor;
        int missOrDup = 0;
        for (int i = 0; i < n; i++) {
            if ((i & differentBit) != 0) {
                missOrDup ^= i;
            }

            if ((nums.get(i) & differentBit) != 0) {
                missOrDup ^= nums.get(i);
            }
        }

        for (Integer num : nums) {
            if (num == missOrDup) {
                return new DuplicateAndMissing(missOrDup, missOrDup ^ xor);
            }
        }

        return new DuplicateAndMissing(missOrDup ^ xor, missOrDup);
    }

    @EpiUserType(ctorParams = {Integer.class, Integer.class})
    public static class DuplicateAndMissing {
        public Integer duplicate;
        public Integer missing;

        public DuplicateAndMissing(Integer duplicate, Integer missing) {
            this.duplicate = duplicate;
            this.missing = missing;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            DuplicateAndMissing that = (DuplicateAndMissing) o;

            if (!duplicate.equals(that.duplicate)) {
                return false;
            }
            return missing.equals(that.missing);
        }

        @Override
        public int hashCode() {
            int result = duplicate.hashCode();
            result = 31 * result + missing.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "duplicate: " + duplicate + ", missing: " + missing;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchForMissingElement.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
