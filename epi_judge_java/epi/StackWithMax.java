package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class StackWithMax {

  public static class Stack {
    private Deque<Node> stack = new LinkedList<>();

    public boolean empty() {
      return stack.isEmpty();
    }

    public Integer max() {
      if (empty()) throw new EmptyStackException();
      return stack.peek().currentMax;
    }

    public Integer pop() {
      if (empty()) throw new EmptyStackException();
      return stack.pop().value;
    }

    public void push(Integer x) {
      stack.push(new Node(x, Math.max(x, empty() ? x : max())));
    }

    private static class Node {
      final Integer value;
      final Integer currentMax;

      public Node(Integer value, Integer currentMax) {
        this.value = value;
        this.currentMax = currentMax;
      }
    }
  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "stack_with_max.tsv")
  public static void stackTester(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
          case "Stack":
            s = new Stack();
            break;
          case "push":
            s.push(op.arg);
            break;
          case "pop":
            result = s.pop();
            if (result != op.arg) {
              throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                  ", got " + String.valueOf(result));
            }
            break;
          case "max":
            result = s.max();
            if (result != op.arg) {
              throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                  ", got " + String.valueOf(result));
            }
            break;
          case "empty":
            result = s.empty() ? 1 : 0;
            if (result != op.arg) {
              throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                  ", got " + String.valueOf(s));
            }
            break;
          default:
            throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StackWithMax.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
