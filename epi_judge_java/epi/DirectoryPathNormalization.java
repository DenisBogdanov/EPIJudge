package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class DirectoryPathNormalization {

  @EpiTest(testDataFile = "directory_path_normalization.tsv")
  public static String shortestEquivalentPath(String path) {
    Deque<String> stack = new LinkedList<>();

    if (path.startsWith("/")) {
      stack.push("/");
    }

    for (String elem : path.split("/")) {
      switch (elem) {
        case ".":
        case "":
          break;
        case "..":
          if (stack.isEmpty() || stack.peek().equals("..")) {
            stack.push(elem);
          } else if (stack.peek().equals("/")) {
            throw new IllegalArgumentException();
          } else {
            stack.pop();
          }
          break;
        default:
          stack.push(elem);
          break;
      }
    }

    StringBuilder sb = new StringBuilder();
    if (!stack.isEmpty()) {
      Iterator<String> it = stack.descendingIterator();
      String prev = it.next();
      sb.append(prev);
      while (it.hasNext()) {
        if (!prev.equals("/")) {
          sb.append("/");
        }

        prev = it.next();
        sb.append(prev);
      }
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
