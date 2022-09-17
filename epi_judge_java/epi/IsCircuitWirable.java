package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class IsCircuitWirable {

    public static boolean isAnyPlacementFeasible(List<GraphVertex> graph) {
        int n = graph.size();
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            graph.get(i).index = i;
        }

        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) continue;
            colors[i] = 1;
            if (!canPaint(graph, i, colors)) {
                return false;
            }
        }

        return true;
    }

    private static boolean canPaint(List<GraphVertex> graph, int nodeIndex, int[] colors) {
        int currColor = colors[nodeIndex];
        for (GraphVertex neighbour : graph.get(nodeIndex).edges) {
            int neighbourIndex = neighbour.index;

            if (colors[neighbourIndex] == currColor) return false;
            if (colors[neighbourIndex] == -currColor) continue;
            colors[neighbourIndex] = -currColor;

            if (!canPaint(graph, neighbourIndex, colors)) {
                return false;
            }
        }

        return true;
    }

    public static class GraphVertex {
        public int index;
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
