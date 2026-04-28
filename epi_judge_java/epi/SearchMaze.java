package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class SearchMaze {
    private static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0},
    };

    public static List<Coordinate> searchMaze(List<List<Color>> maze, Coordinate s, Coordinate e) {
        int n = maze.size();
        int m = maze.get(0).size();
        Coordinate[][] prev = new Coordinate[n][m];
        prev[s.x][s.y] = new Coordinate(-1, -1);
        Queue<Coordinate> q = new ArrayDeque<>();
        q.offer(s);
        while (!q.isEmpty()) {
            var polled = q.poll();
            for (var dir : DIRS) {
                int newR = polled.x + dir[0];
                int newC = polled.y + dir[1];

                if (newR == e.x && newC == e.y) {
                    prev[newR][newC] = polled;
                    return createPath(prev, e);
                }

                if (newR < 0 || newR == n || newC < 0 || newC == m) continue;
                if (prev[newR][newC] != null || maze.get(newR).get(newC) == Color.BLACK) continue;
                q.offer(new Coordinate(newR, newC));
                prev[newR][newC] = polled;
            }

        }

        return Collections.emptyList();
    }

    private static List<Coordinate> createPath(Coordinate[][] prev, Coordinate e) {
        Coordinate curr = e;
        List<Coordinate> ans = new ArrayList<>();
        while (curr.x != -1) {
            ans.add(curr);
            curr = prev[curr.x][curr.y];
        }
        Collections.reverse(ans);
        return ans;
    }

    @EpiUserType(ctorParams = {int.class, int.class})
    public static class Coordinate {
        public int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Coordinate that = (Coordinate) o;
            if (x != that.x || y != that.y) {
                return false;
            }
            return true;
        }
    }

    public enum Color {WHITE, BLACK}

    public static boolean pathElementIsFeasible(List<List<Integer>> maze,
                                                Coordinate prev, Coordinate cur) {
        if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y &&
                cur.y < maze.get(cur.x).size() && maze.get(cur.x).get(cur.y) == 0)) {
            return false;
        }
        return cur.x == prev.x + 1 && cur.y == prev.y ||
                cur.x == prev.x - 1 && cur.y == prev.y ||
                cur.x == prev.x && cur.y == prev.y + 1 ||
                cur.x == prev.x && cur.y == prev.y - 1;
    }

    @EpiTest(testDataFile = "search_maze.tsv")
    public static boolean searchMazeWrapper(List<List<Integer>> maze,
                                            Coordinate s, Coordinate e)
            throws TestFailure {
        List<List<Color>> colored = new ArrayList<>();
        for (List<Integer> col : maze) {
            List<Color> tmp = new ArrayList<>();
            for (Integer i : col) {
                tmp.add(i == 0 ? Color.WHITE : Color.BLACK);
            }
            colored.add(tmp);
        }
        List<Coordinate> path = searchMaze(colored, s, e);
        if (path.isEmpty()) {
            return s.equals(e);
        }

        if (!path.get(0).equals(s) || !path.get(path.size() - 1).equals(e)) {
            throw new TestFailure("Path doesn't lay between start and end points");
        }

        for (int i = 1; i < path.size(); i++) {
            if (!pathElementIsFeasible(maze, path.get(i - 1), path.get(i))) {
                throw new TestFailure("Path contains invalid segments");
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchMaze.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
