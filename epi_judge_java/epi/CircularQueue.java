package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CircularQueue {

  public static class Queue {
    private static final int SCALE_FACTOR = 2;

    private int start = 0;
    private int end = 0;
    private int size = 0;
    private Integer[] storage;

    public Queue(int capacity) {
      this.storage = new Integer[capacity];
    }

    public void enqueue(Integer x) {
      if (size == storage.length) {
        Collections.rotate(Arrays.asList(storage), -start);
        start = 0;
        end = size;
        storage = Arrays.copyOf(storage, size * SCALE_FACTOR);
      }

      size++;

      if (end == storage.length - 1) {
        storage[end] = x;
        end = 0;
      } else {
        storage[end++] = x;
      }
    }

    public Integer dequeue() {
      if (size == 0) throw new IllegalStateException();

      size--;

      if (start == storage.length - 1) {
        start = 0;
        return storage[storage.length - 1];
      } else {
        start++;
        return storage[start - 1];
      }
    }

    public int size() {
      return size;
    }

    @Override
    public String toString() {
      return super.toString();
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

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
        case "Queue":
          q = new Queue(op.arg);
          break;
        case "enqueue":
          q.enqueue(op.arg);
          break;
        case "dequeue":
          int result = q.dequeue();
          if (result != op.arg) {
            throw new TestFailure()
                .withProperty(TestFailure.PropertyName.STATE, q)
                .withProperty(TestFailure.PropertyName.COMMAND, op)
                .withMismatchInfo(opIdx, op.arg, result);
          }
          break;
        case "size":
          int s = q.size();
          if (s != op.arg) {
            throw new TestFailure()
                .withProperty(TestFailure.PropertyName.STATE, q)
                .withProperty(TestFailure.PropertyName.COMMAND, op)
                .withMismatchInfo(opIdx, op.arg, s);
          }
          break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
