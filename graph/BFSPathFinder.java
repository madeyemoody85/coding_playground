package graph;

import java.util.*;

/**
 * Given n x n grid find path from start point to end point
 * Created by nachiketlondhe on 2/26/17.
 */
public class BFSPathFinder {

    class Node {
        int x;
        int y;
        Node parent;
        public Node(int _x, int _y, Node _parent) {
            this.x = _x;
            this.y = _y;
            this.parent = _parent;
        }
    }

    public static void main(String args[]) {
        String[] grid = new String[4];
        grid[0] = "0110";
        grid[1] = "0110";
        grid[2] = "0110";
        grid[3] = "0000";

        BFSPathFinder solution = new BFSPathFinder();
        solution.findPath(grid, new int[]{0, 0}, new int[]{3, 3});
    }

    public void findPath(String[] inputGrid, int[] start, int[] end) {

        int gridWidth = inputGrid[0].length();
        int gridHeight = inputGrid.length;

        int[][] grid = new int[gridHeight][gridWidth];

        for (int i = 0; i < gridHeight; i++) {
            String temp = inputGrid[i];
            for (int j = 0; j < temp.length(); j++) {
                grid[i][j] = temp.charAt(j) - 48;
            }
        }

        Node path = bfs(grid, start, end);

        if (path != null) {
            path = correctedPath(path);
            while (path != null) {
                System.out.print("["+path.x+","+path.y+"] --> ");
                path = path.parent;
            }
        } else {
            System.out.println("Path not found");
        }
    }

    /*
          0 1 2 3

       0  0 0 0 1
       1  0 0 1 0
       2  0 1 1 0
       3  1 0 1 0
     */
    public Node bfs(int[][] grid, int[] _start, int[] _end) {

        // To keep track of the ship we have visited
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        Queue<Node> queue = new LinkedList<>();

        Node start = new Node(_start[0], _start[1], null);

        queue.add(start);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            // Reached the end node
            if (temp.x == _end[0] && temp.y == _end[1] ) {
                return temp;
            }

            if (temp.x < 0 || temp.x >= grid.length) continue;

            if (temp.y < 0 || temp.y >= grid[0].length) continue;

            if (!visited[temp.x][temp.y]) {
                visited[temp.x][temp.y] = true;

                for (int xDelta = 0; xDelta <= 1; xDelta++) {
                    for (int yDelta = 0; yDelta <= 1; yDelta++) {
                        if (temp.x + xDelta < grid.length && temp.y + yDelta < grid[0].length &&
                                grid[temp.x + xDelta][temp.y + yDelta] == 0) {
                            Node x = new Node(temp.x + xDelta, temp.y + yDelta, temp);
                            queue.add(x);
                        }
                    }
                }
            }
        }

        return null;
    }

    public Node correctedPath(Node path) {
        if (path.parent == null) {
            return path;
        }

        Node prev = null;
        Node current = path;
        Node next = path.parent;

        while (current != null) {
            current.parent = prev;
            prev = current;
            current = next;
            if (next != null) {
                next = next.parent;
            }
        }

        path = prev;
        return path;
    }
}