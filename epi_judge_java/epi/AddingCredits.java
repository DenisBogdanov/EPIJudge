package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AddingCredits {

    public static class ClientsCreditsInfo {
        private final TreeMap<Integer, Set<String>> creditsToClientsMap = new TreeMap<>(Comparator.reverseOrder());
        private final Map<String, Integer> clientToCreditsMap = new HashMap<>();
        private int added = 0;

        public void insert(String clientID, int c) {
            c -= added;
            clientToCreditsMap.put(clientID, c);
            creditsToClientsMap.computeIfAbsent(c, k -> new HashSet<>())
                    .add(clientID);
        }

        public boolean remove(String clientID) {
            Integer credits = clientToCreditsMap.remove(clientID);
            if (credits == null) return false;

            creditsToClientsMap.get(credits).remove(clientID);
            if (creditsToClientsMap.get(credits).isEmpty()) {
                creditsToClientsMap.remove(credits);
            }

            return true;
        }

        public int lookup(String clientID) {
            Integer credits = clientToCreditsMap.get(clientID);
            return credits == null ? -1 : credits + added;
        }

        public void addAll(int c) {
            added += c;
        }

        public String max() {
            return creditsToClientsMap.firstEntry().getValue().iterator().next();
        }

        @Override
        public String toString() {
            // TODO - you fill in here.
            return super.toString();
        }
    }

    @EpiUserType(ctorParams = {String.class, String.class, int.class})
    public static class Operation {
        public String op;
        public String sArg;
        public int iArg;

        public Operation(String op, String sArg, int iArg) {
            this.op = op;
            this.sArg = sArg;
            this.iArg = iArg;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Operation operation = (Operation) o;

            if (iArg != operation.iArg) {
                return false;
            }
            if (!op.equals(operation.op)) {
                return false;
            }
            return sArg.equals(operation.sArg);
        }

        @Override
        public int hashCode() {
            int result = op.hashCode();
            result = 31 * result + sArg.hashCode();
            result = 31 * result + iArg;
            return result;
        }

        @Override
        public String toString() {
            return String.format("%s(%s, %d)", op, sArg, iArg);
        }
    }

    @EpiTest(testDataFile = "adding_credits.tsv")
    public static void ClientsCreditsInfoTester(List<Operation> ops)
            throws TestFailure {
        ClientsCreditsInfo cr = new ClientsCreditsInfo();
        int opIdx = 0;
        for (Operation x : ops) {
            String sArg = x.sArg;
            int iArg = x.iArg;
            int result;
            switch (x.op) {
                case "ClientsCreditsInfo":
                    break;
                case "remove":
                    result = cr.remove(sArg) ? 1 : 0;
                    if (result != iArg) {
                        throw new TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, cr)
                                .withProperty(TestFailure.PropertyName.COMMAND, x)
                                .withMismatchInfo(opIdx, iArg, result);
                    }
                    break;
                case "insert":
                    cr.insert(sArg, iArg);
                    break;
                case "add_all":
                    cr.addAll(iArg);
                    break;
                case "lookup":
                    result = cr.lookup(sArg);
                    if (result != iArg) {
                        throw new TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, cr)
                                .withProperty(TestFailure.PropertyName.COMMAND, x)
                                .withMismatchInfo(opIdx, iArg, result);
                    }
            }
            opIdx++;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "AddingCredits.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
