package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class Knapsack {

  @EpiTest(testDataFile = "knapsack.tsv")
  public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
    int[][] itemToAvailableCapacityArr = new int[items.size()][capacity + 1];
    for (int[] itemsArr : itemToAvailableCapacityArr) {
      Arrays.fill(itemsArr, -1);
    }

    return solve(items, items.size() - 1, capacity, itemToAvailableCapacityArr);
  }

  private static int solve(List<Item> items, int itemIndex, int availableCapacity, int[][] itemToCapacityArr) {
    if (itemIndex < 0) {
      return 0;
    }

    if (itemToCapacityArr[itemIndex][availableCapacity] == -1) {
      int withoutCurrItem = solve(items, itemIndex - 1, availableCapacity, itemToCapacityArr);
      Item currItem = items.get(itemIndex);
      int withCurrItem = availableCapacity < currItem.weight
          ? 0
          : currItem.value
          + solve(items, itemIndex - 1, availableCapacity - currItem.weight, itemToCapacityArr);

      itemToCapacityArr[itemIndex][availableCapacity] = Math.max(withCurrItem, withoutCurrItem);
    }

    return itemToCapacityArr[itemIndex][availableCapacity];
  }

  @EpiUserType(ctorParams = {Integer.class, Integer.class})
  public static class Item {
    public Integer weight;
    public Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Knapsack.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
