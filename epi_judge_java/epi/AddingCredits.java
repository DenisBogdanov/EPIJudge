package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class AddingCredits {

  public static class ClientsCreditsInfo {
    private int addedToAll = 0;
    private final Map<String, Integer> clientToCreditMap = new HashMap<>();
    private final NavigableMap<Integer, Set<String>> creditsToClientsMap = new TreeMap<>();

    public void insert(String clientID, int credits) {
      remove(clientID);
      clientToCreditMap.put(clientID, credits - addedToAll);
      creditsToClientsMap.computeIfAbsent(credits - addedToAll, k -> new HashSet<>()).add(clientID);
    }

    public boolean remove(String clientID) {
      Integer credit = clientToCreditMap.get(clientID);
      if (credit != null) {
        creditsToClientsMap.get(credit).remove(clientID);
        if (creditsToClientsMap.get(credit).isEmpty()) {
          creditsToClientsMap.remove(credit);
        }

        clientToCreditMap.remove(clientID);
        return true;
      }

      return false;
    }

    public int lookup(String clientID) {
      Integer credit = clientToCreditMap.get(clientID);
      return credit == null ? -1 : credit + addedToAll;
    }

    public void addAll(int c) {
      addedToAll += c;
    }

    public String max() {
      return creditsToClientsMap.isEmpty()
          ? ""
          : creditsToClientsMap.lastEntry().getValue().iterator().next();
    }

    @Override
    public String toString() {
      // TODO - you fill in here.
      return super.toString();
    }

    private static class Client {
      final String id;
      int credits;

      public Client(String id) {
        this.id = id;
      }

      public Client(String id, int credits) {
        this.id = id;
        this.credits = credits;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id);
      }

      @Override
      public int hashCode() {
        return Objects.hash(id);
      }

      @Override
      public String toString() {
        return "Client{" +
            "id='" + id + '\'' +
            ", credits=" + credits +
            '}';
      }
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
