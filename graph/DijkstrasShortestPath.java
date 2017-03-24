package graph;

import java.util.*;

public class DijkstrasShortestPath {

    class Vertex {
        int key;
        List<Edge> edges;
        int dist;

        public Vertex(int _key) {
            this.key = _key;
            this.edges = new ArrayList<>();
        }
    }

    class Edge {
        Vertex source;
        Vertex destination;
        int weight;

        public Edge(Vertex _source, Vertex _destination, int _weight) {
            this.source = _source;
            this.destination = _destination;
            this.weight = _weight;
        }
    }

    public void dijkstras(Graph g, Vertex source) {
//        for (Vertex v : Graph.vertices) {
//            v.dist = Integer.MAX_VALUE;
//            queue.add(v);
//        }

        source.dist = 0;

        // Priority queue with minimum weight on top
        Queue<Vertex> queue = new PriorityQueue<>((v1, v2) -> (v1.dist - v2.dist));

        // map to keep track of parents of this node
        HashMap<Vertex, Vertex> parentMap = new HashMap<>();

        parentMap.put(source, null);

        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();
            for (Edge e : u.edges) {
                Vertex v = getDestination(e);
                if (u.dist + e.weight < v.dist) {
                    v.dist = u.dist + e.weight;
                    parentMap.put(v, u);
                }
            }
        }
    }

    public Vertex getDestination(Edge e) {
        return e.destination;
    }
}
