package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeadlockDetection {
    private static boolean hasCycle;

    public static boolean isDeadlocked(List<GraphVertex> graph) {
        hasCycle = false;
        Set<GraphVertex> visited = new HashSet<>();
        for (GraphVertex graphVertex : graph) {
            if (visited.contains(graphVertex)) continue;
            hasCycleDfs(graphVertex, graphVertex, visited);
            if (hasCycle) return true;
        }

        return false;
    }

    private static void hasCycleDfs(GraphVertex curr, GraphVertex prev, Set<GraphVertex> visited) {
        if (hasCycle) return;

        visited.add(curr);
        for (GraphVertex neighbour : curr.edges) {
            if (neighbour.edges.contains(curr)) {
                hasCycle = true;
                return;
            }
            if (!visited.contains(neighbour)) {
                hasCycleDfs(neighbour, curr, visited);
            } else if (neighbour != prev) {
                hasCycle = true;
                return;
            }
        }
    }

    public static class GraphVertex {
        public List<GraphVertex> edges;

        public GraphVertex() {
            edges = new ArrayList<>();
        }
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

    @EpiTest(testDataFile = "deadlock_detection.tsv")
    public static boolean isDeadlockedWrapper(TimedExecutor executor,
                                              int numNodes, List<Edge> edges)
            throws Exception {
        if (numNodes <= 0) {
            throw new RuntimeException("Invalid numNodes value");
        }
        List<GraphVertex> graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new GraphVertex());
        }
        for (Edge e : edges) {
            if (e.from < 0 || e.from >= numNodes || e.to < 0 || e.to >= numNodes) {
                throw new RuntimeException("Invalid vertex index");
            }
            graph.get(e.from).edges.add(graph.get(e.to));
        }

        return executor.run(() -> isDeadlocked(graph));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DeadlockDetection.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
