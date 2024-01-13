package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.List;

public class CircularQueue {

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

    public static class Queue {
        int start = 0;
        int end = 0;
        int size = 0;
        private int[] storage;

        public Queue(int capacity) {
            if (capacity <= 0) throw new IllegalArgumentException();

            storage = new int[capacity];
        }

        public void enqueue(Integer x) {
            int storageSize = storage.length;
            if (size() == storageSize) {
                int[] temp = new int[storageSize * 2];
                for (int i = start; i < end + storageSize; i++) {
                    temp[i - start] = storage[i % storageSize];
                }

                start = 0;
                end = storageSize;
                storage = temp;
                storageSize *= 2;
            }

            storage[end % storageSize] = x;
            end++;
            size++;
            if (end == storageSize) end = 0;
        }

        public Integer dequeue() {
            if (size() == 0) throw new IllegalStateException();
            int result = storage[start];
            start++;
            if (start == storage.length) {
                start = 0;
            }

            size--;
            return result;
        }

        public int size() {
            return size;
        }

        @Override
        public String toString() {
            // TODO - you fill in here.
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
}
