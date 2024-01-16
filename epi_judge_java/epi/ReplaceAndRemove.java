package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class ReplaceAndRemove {

    public static int replaceAndRemove(int size, char[] s) {
        int index = 0;
        int aCount = 0;
        for (int i = 0; i < size; i++) {
            if (s[i] != 'b') {
                s[index++] = s[i];
                if (s[i] == 'a') aCount++;
            }
        }

        int resultSize = index + aCount;
        int backwardIndex = resultSize - 1;
        for (int i = index - 1; i >= 0; i--) {
            if (s[i] == 'a') {
                s[backwardIndex--] = 'd';
                s[backwardIndex--] = 'd';
            } else {
                s[backwardIndex--] = s[i];
            }
        }

        return resultSize;
    }

    @EpiTest(testDataFile = "replace_and_remove.tsv")
    public static List<String> replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
            throws Exception {
        char[] sCopy = new char[s.size()];
        for (int i = 0; i < size; ++i) {
            if (!s.get(i).isEmpty()) {
                sCopy[i] = s.get(i).charAt(0);
            }
        }

        Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

        List<String> result = new ArrayList<>();
        for (int i = 0; i < resSize; ++i) {
            result.add(Character.toString(sCopy[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
