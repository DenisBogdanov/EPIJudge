package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class ReplaceAndRemove {

  public static int replaceAndRemove(int size, char[] chars) {
    int aCount = 0;

    int currentPosition = 0;

    for (int i = 0; i < size; i++) {
      if (chars[i] == 'a') {
        aCount++;
      }

      if (chars[i] != 'b') {
        chars[currentPosition++] = chars[i];
      }
    }

    int finalSize = currentPosition + aCount;

    int indexToInsert = currentPosition - 1 + aCount;

    for (int i = currentPosition - 1; i >= 0; i--) {
      if (chars[i] == 'a') {
        chars[indexToInsert--] = 'd';
        chars[indexToInsert--] = 'd';
      } else {
        chars[indexToInsert--] = chars[i];
      }
    }

    return finalSize;
  }

  @EpiTest(testDataFile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
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
