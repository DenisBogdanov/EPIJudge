package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SunsetView {
  public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
    if (!sequence.hasNext()) {
      return List.of();
    }

    LinkedList<Building> stack = new LinkedList<>();

    int id = 0;

    stack.push(new Building(id++, sequence.next()));

    while (sequence.hasNext()) {
      Integer next = sequence.next();

      while (!stack.isEmpty() && stack.peek().height <= next) {
        stack.pop();
      }

      stack.push(new Building(id++, next));
    }


    return stack.stream()
        .mapToInt(b -> b.id)
        .boxed()
        .collect(Collectors.toList());
  }

  private static class Building {
    final int id;
    final int height;

    public Building(int id, int height) {
      this.id = id;
      this.height = height;
    }
  }

  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
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
