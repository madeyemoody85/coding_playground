package graph;

import java.util.*;

/**
 *  Graph implementation For DFS BFS and for Dijkstras
 */
public class Graph {

    int graphSize;
    Map<Character, List<Character>> adjacencyList;

    public Graph(int numberOfVertices) {
        this.graphSize = numberOfVertices;
        this.adjacencyList = new HashMap();
    }

    public void addVertex(char vertex) {
        if (this.adjacencyList.size() >= this.graphSize) {
            throw new IllegalStateException("Graph capacity reached");
        }
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new ArrayList<>());
        } else {
            System.out.println("Node already exists");
        }
    }

    public void addEdge(char source, char destination) {
        if (adjacencyList.containsKey(source) && adjacencyList.containsKey(destination)) {
            adjacencyList.get(source).add(destination);
            adjacencyList.get(destination).add(source);
        }
    }

    public void dfs(char root) {
        Stack<Character> stack = new Stack<>();
        Set<Character> visited = new HashSet<>();
        stack.push(root);
        while (!stack.empty()) {
            Character temp = stack.pop();
            if (!visited.contains(temp)) {
                System.out.print(temp + " ");
                visited.add(temp);

                for (Character c : adjacencyList.get(temp)) {
                    if (!visited.contains(c)) {
                        stack.push(c);
                    }
                }
            }
        }
    }

    public void bfsWrong(char root) {
        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Character temp = queue.poll();
            if (!visited.contains(temp)) {
                System.out.print(temp + " ");
                visited.add(temp);

                for (Character c : adjacencyList.get(temp)) {
                    if (!visited.contains(c)) {
                        queue.add(c);
                    }
                }
            }
        }
    }

    public void bfs(char root) {
        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Character top = queue.poll();
            if (!visited.contains(top)) {
                System.out.print(top + " ");
                visited.add(top);
                for (Character c : adjacencyList.get(top)) {
                    queue.add(c);
                }
            }
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(6);

        g.addVertex('a');
        g.addVertex('b');
        g.addVertex('c');
        g.addVertex('d');
        g.addVertex('e');
        g.addVertex('f');

        g.addEdge('a','b');
        g.addEdge('a','c');
        g.addEdge('a','d');
        g.addEdge('b','e');
        g.addEdge('b','f');
        g.addEdge('c','f');

        g.dfs('a');

        System.out.println();

        g.bfs('a');
    }
}
