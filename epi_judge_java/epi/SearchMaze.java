package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class SearchMaze {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static List<Coordinate> searchMaze(List<List<Color>> maze, Coordinate s, Coordinate e) {
        int rows = maze.size();
        int columns = maze.get(0).size();

        Coordinate[][] prev = new Coordinate[rows][columns];
        Queue<Coordinate> q = new ArrayDeque<>();
        q.offer(s);

        boolean[][] visited = new boolean[rows][columns];
        visited[s.x][s.y] = true;

        while (!q.isEmpty()) {
            Coordinate polled = q.poll();
            for (int[] dir : DIRECTIONS) {
                int nextRow = polled.x + dir[0];
                int nextCol = polled.y + dir[1];

                if (nextRow == e.x && nextCol == e.y) {
                    List<Coordinate> result = new ArrayList<>();
                    prev[nextRow][nextCol] = polled;
                    Coordinate curr = new Coordinate(nextRow, nextCol);
                    while (curr != null) {
                        result.add(curr);
                        curr = prev[curr.x][curr.y];
                    }

                    Collections.reverse(result);
                    return result;
                }

                if (nextRow < 0 || nextRow == rows || nextCol < 0 || nextCol == columns) continue;
                if (visited[nextRow][nextCol] || maze.get(nextRow).get(nextCol) == Color.BLACK) continue;
                visited[nextRow][nextCol] = true;

                prev[nextRow][nextCol] = polled;
                q.offer(new Coordinate(nextRow, nextCol));
            }
        }

        return Collections.emptyList();
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
