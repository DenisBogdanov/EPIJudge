package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeadlockDetection {

    public static boolean isDeadlocked(List<GraphVertex> graph) {
        int n = graph.size();
        Map<GraphVertex, Integer> nodeToPathIndexMap = new HashMap<>();
        int currIndex = 0;

        for (GraphVertex currNode : graph) {
            if (nodeToPathIndexMap.containsKey(currNode)) continue;

            currIndex++;
            nodeToPathIndexMap.put(currNode, currIndex);
            if (hasCycleDfs(currNode, nodeToPathIndexMap, currIndex)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasCycleDfs(GraphVertex currNode, Map<GraphVertex, Integer> nodeToPathIndexMap, int currIndex) {
        for (GraphVertex neighbour : currNode.edges) {
            if (nodeToPathIndexMap.getOrDefault(neighbour, 0) == currIndex) {
                return true;
            }

            if (nodeToPathIndexMap.containsKey(neighbour)) continue;
            nodeToPathIndexMap.put(neighbour, currIndex);

            if (hasCycleDfs(neighbour, nodeToPathIndexMap, currIndex)) {
                return true;
            }
        }

        return false;
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
