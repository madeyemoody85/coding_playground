package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by nachiketlondhe on 1/23/16.
 */
public class Graph {

    ArrayList<Node> adjacencyList = new ArrayList();

    public static void main(String args[]) {

        Graph graph = new Graph();

        Node one        = new Node(1);
        Node two        = new Node(2);
        Node three      = new Node(3);
        Node four       = new Node(4);
        Node five       = new Node(5);
        Node six        = new Node(6);

        one.edges.add(two);
        one.edges.add(three);
        one.edges.add(five);

        two.edges.add(one);
        two.edges.add(four);

        three.edges.add(one);
        three.edges.add(four);
        three.edges.add(six);

        four.edges.add(two);
        four.edges.add(three);

        five.edges.add(one);

        six.edges.add(three);

        graph.addNode(one);
        graph.addNode(two);
        graph.addNode(three);
        graph.addNode(four);
        graph.addNode(five);
        graph.addNode(six);

        System.out.println("Graph Created");

        //Here we get the first node of the graph by accessing its adjacency list
        graph.dfs(one);

        graph.clearVisits();

        graph.bfs(six);

        graph.clearVisits();

    }

    public void addNode(Node node) {

        adjacencyList.add(node);
        System.out.println("Node added to the graph: " + node.data);

    }

    public void dfs(Node startNode) {

        Stack<Node> stack = new Stack();

        stack.push(startNode);

        while(!stack.isEmpty()) {

            Node top = stack.peek();

            stack.pop();

            if(!top.visited) {

                System.out.println("Visiting node: " + top.data);
                top.visited = true;

                for(Node n : top.edges) {

                    if(!n.visited) {
                        stack.push(n);
                    }
                }

            }
        }
    }

    public void bfs(Node startNode) {

        Queue<Node> queue = new LinkedList<Node>();

        queue.add(startNode);

        System.out.println("Visiting node: " + startNode.data);

        while(!queue.isEmpty()) {

            Node temp = queue.remove();
            temp.visited = true;

            for(Node n : temp.edges) {

                if(!n.visited) {
                    n.visited = true;
                    System.out.println("Visiting node: " + n.data);
                    queue.add(n);
                }
            }
        }

    }

    public void clearVisits() {

        System.out.println(System.lineSeparator());
        for(Node node : adjacencyList) {
            node.visited = false;
        }
    }

    static class Node {

        int             data;
        boolean         visited;
        ArrayList<Node> edges;

        public Node(int data) {

            this.data       = data;
            this.visited    = false;
            this.edges      = new ArrayList<>();

        }
    }



}
