package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IsCircuitWirable {

  public static boolean isAnyPlacementFeasible(List<GraphVertex> graph) {
    for (GraphVertex vertex : graph) {
      if (vertex.color != 0) continue;

      Queue<GraphVertex> q = new LinkedList<>();
      q.offer(vertex);
      vertex.color = 1;

      while (!q.isEmpty()) {
        GraphVertex polled = q.poll();
        for (GraphVertex neighbour : polled.edges) {
          if (neighbour.color == 0) {
            neighbour.color = -polled.color;
            q.offer(neighbour);
          } else if (neighbour.color == polled.color) {
            return false;
          }
        }
      }
    }

    return true;
  }

  public static class GraphVertex {
    int color = 0;
    public List<GraphVertex> edges = new ArrayList<>();
  }

  @EpiUserType(ctorParams = {int.class, int.class})
  public static class Edge {
    public int from;
    public int to;

    public Edge(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }

  @EpiTest(testDataFile = "is_circuit_wirable.tsv")
  public static boolean isAnyPlacementFeasibleWrapper(TimedExecutor executor,
                                                      int k, List<Edge> edges)
      throws Exception {
    if (k <= 0)
      throw new RuntimeException("Invalid k value");
    List<GraphVertex> graph = new ArrayList<>();
    for (int i = 0; i < k; i++)
      graph.add(new GraphVertex());
    for (Edge e : edges) {
      if (e.from < 0 || e.from >= k || e.to < 0 || e.to >= k)
        throw new RuntimeException("Invalid vertex index");
      graph.get(e.from).edges.add(graph.get(e.to));
    }

    return executor.run(() -> isAnyPlacementFeasible(graph));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsCircuitWirable.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
