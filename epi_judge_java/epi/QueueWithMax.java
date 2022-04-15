package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class QueueWithMax {
  private final Deque<Integer> queue = new LinkedList<>();
  private final Deque<Pair> maxDeque = new LinkedList<>();

  public void enqueue(Integer x) {
    queue.offer(x);

    while (!maxDeque.isEmpty() && x > maxDeque.getLast().value) {
      maxDeque.removeLast();
    }

    if (!maxDeque.isEmpty() && x.equals(maxDeque.peek().value)) {
      maxDeque.getLast().count++;
    } else {
      maxDeque.addLast(new Pair(x));
    }
  }

  public Integer dequeue() {
    if (queue.isEmpty()) throw new IllegalStateException();

    Integer polled = queue.poll();

    if (polled.equals(maxDeque.getFirst().value)) {
      if (maxDeque.getFirst().count == 1) {
        maxDeque.poll();
      } else {
        maxDeque.getFirst().count--;
      }
    }

    return polled;
  }

  public Integer max() {
    if (queue.isEmpty()) throw new IllegalStateException();
    return maxDeque.getFirst().value;
  }

  private static class Pair {
    final Integer value;
    Integer count;

    public Pair(Integer value) {
      this.value = value;
      this.count = 1;
    }
  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "queue_with_max.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    try {
      QueueWithMax q = new QueueWithMax();

      for (QueueOp op : ops) {
        switch (op.op) {
          case "QueueWithMax":
            q = new QueueWithMax();
            break;
          case "enqueue":
            q.enqueue(op.arg);
            break;
          case "dequeue":
            int result = q.dequeue();
            if (result != op.arg) {
              throw new TestFailure("Dequeue: expected " +
                  String.valueOf(op.arg) + ", got " +
                  String.valueOf(result));
            }
            break;
          case "max":
            int s = q.max();
            if (s != op.arg) {
              throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                  ", got " + String.valueOf(s));
            }
            break;
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "QueueWithMax.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
