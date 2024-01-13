package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;

public class StackWithMax {
    public static class Stack {
        private Deque<Elem> storage = new ArrayDeque<>();

        public boolean empty() {
            return storage.isEmpty();
        }

        public Integer max() {
            if (empty()) throw new EmptyStackException();
            return storage.peek().currMax;
        }

        public Integer pop() {
            if (empty()) throw new EmptyStackException();
            return storage.pop().value;
        }

        public void push(Integer x) {
            if (empty()) {
                storage.push(new Elem(x, x));
            } else {
                Integer currMax = max();
                storage.push(new Elem(x, Math.max(currMax, x)));
            }
        }

        private static class Elem {
            final int value;
            final int currMax;

            public Elem(int value, int currMax) {
                this.value = value;
                this.currMax = currMax;
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
