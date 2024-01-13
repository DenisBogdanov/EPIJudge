package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public class QueueWithMax {
    private final Deque<Integer> maxStack = new ArrayDeque<>();
    private final Deque<Integer> q = new ArrayDeque<>();

    public void enqueue(Integer x) {
        q.offer(x);
        while (!maxStack.isEmpty() && maxStack.peek() < x) {
            maxStack.pop();
        }

        maxStack.push(x);
    }

    public Integer dequeue() {
        if (q.isEmpty()) throw new IllegalStateException();
        int polled = q.poll();
        if (maxStack.peekLast() == polled) {
            maxStack.pollLast();
        }

        return polled;
    }

    public Integer max() {
        if (q.isEmpty()) throw new IllegalStateException();
        return maxStack.peekLast();
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
