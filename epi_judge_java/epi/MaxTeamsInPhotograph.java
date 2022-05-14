package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MaxTeamsInPhotograph {

  public static int findLargestNumberTeams(List<GraphVertex> graph) {
    Deque<GraphVertex> orderStack = buildTopologicalOrdering(graph);
    return findLongestPath(orderStack);
  }

  private static int findLongestPath(Deque<GraphVertex> orderStack) {
    int result = 0;

    while (!orderStack.isEmpty()) {
      GraphVertex popped = orderStack.pop();
      result = Math.max(result, popped.maxDistance);
      for (GraphVertex neighbour : popped.edges) {
        neighbour.maxDistance = Math.max(neighbour.maxDistance, popped.maxDistance + 1);
      }
    }

    return result;
  }

  private static Deque<GraphVertex> buildTopologicalOrdering(List<GraphVertex> graph) {
    Deque<GraphVertex> orderStack = new LinkedList<>();
    for (GraphVertex vertex : graph) {
      if (!vertex.visited) {
        dfs(vertex, orderStack);
      }
    }

    return orderStack;
  }

  private static void dfs(GraphVertex currVertex, Deque<GraphVertex> orderStack) {
    currVertex.visited = true;
    for (GraphVertex neighbour : currVertex.edges) {
      if (!neighbour.visited) {
        dfs(neighbour, orderStack);
      }
    }

    orderStack.push(currVertex);
  }

  public static class GraphVertex {
    public List<GraphVertex> edges = new ArrayList<>();
    // Set maxDistance = 0 to indicate unvisited vertex.
    public int maxDistance = 1;
    public boolean visited;
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

  @EpiTest(testDataFile = "max_teams_in_photograph.tsv")
  public static int findLargestNumberTeamsWrapper(TimedExecutor executor, int k,
                                                  List<Edge> edges)
      throws Exception {
    if (k <= 0) {
      throw new RuntimeException("Invalid k value");
    }
    List<GraphVertex> graph = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      graph.add(new GraphVertex());
    }
    for (Edge e : edges) {
      if (e.from < 0 || e.from >= k || e.to < 0 || e.to >= k) {
        throw new RuntimeException("Invalid vertex index");
      }
      graph.get(e.from).edges.add(graph.get(e.to));
    }

    return executor.run(() -> findLargestNumberTeams(graph));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxTeamsInPhotograph.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
