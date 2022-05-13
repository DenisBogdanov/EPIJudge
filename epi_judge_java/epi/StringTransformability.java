package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class StringTransformability {

  @EpiTest(testDataFile = "string_transformability.tsv")
  public static int transformString(Set<String> dict, String s, String t) {
//    return verySlowSolution(dict, s, t);


    Queue<StringWithDistance> q = new LinkedList<>();
    dict.remove(s);
    q.offer(new StringWithDistance(s, 0));

    StringWithDistance strWithDist;
    while ((strWithDist = q.poll()) != null) {
      if (strWithDist.candidate.equals(t)) {
        return strWithDist.distance;
      }

      String candidate = strWithDist.candidate;

      for (int i = 0; i < candidate.length(); i++) {
        String start = candidate.substring(0, i);
        String end = i + 1 < candidate.length() ? candidate.substring(i + 1) : "";
        for (int j = 0; j < 26; j++) {
          String modStr = start + (char) ('a' + j) + end;
          if (dict.remove(modStr)) {
            q.add(new StringWithDistance(modStr, strWithDist.distance + 1));
          }
        }
      }
    }

    return -1;
  }

  private static class StringWithDistance {
    final String candidate;
    final int distance;

    StringWithDistance(String candidate, int distance) {
      this.candidate = candidate;
      this.distance = distance;
    }
  }

  private static int verySlowSolution(Set<String> dict, String s, String t) {
    Map<String, List<String>> adjList = new HashMap<>();
    for (String a : dict) {
      List<String> neighbours = new ArrayList<>();
      for (String b : dict) {
        if (isOneCharDiff(a, b)) {
          neighbours.add(b);
        }
      }

      adjList.put(a, neighbours);
    }

    Queue<String> q = new ArrayDeque<>();
    q.offer(s);
    Set<String> visited = new HashSet<>();

    int result = 0;

    while (!q.isEmpty()) {
      result++;
      int size = q.size();

      for (int i = 0; i < size; i++) {
        String polled = q.poll();
        if (visited.contains(polled)) continue;
        visited.add(polled);

        for (String neighbour : adjList.get(polled)) {
          if (neighbour.equals(t)) return result;
          if (!visited.contains(neighbour)) {
            q.offer(neighbour);
          }
        }
      }
    }

    return -1;
  }

  private static boolean isOneCharDiff(String a, String b) {
    if (a.length() != b.length()) return false;

    int count = 0;

    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) != b.charAt(i)) {
        count++;

        if (count > 1) {
          return false;
        }
      }
    }

    return count == 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringTransformability.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
