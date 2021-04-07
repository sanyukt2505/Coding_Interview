package algos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    public static class Vertex {
        public int data;
        public boolean isVisited;
        public List<Vertex> neighbourList;

        public Vertex(int data) {
            this.data = data;
            this.neighbourList = new ArrayList<>();
        }
    }

    private Map<Integer, Vertex> vertexMap = new HashMap<Integer, Vertex>();

    public void cleanUp() {
        for(Integer key: vertexMap.keySet()) {
            vertexMap.get(key).isVisited = false;
        }
    }

    public void addVertex (Vertex node) {
        vertexMap.put(node.data, node);
    }

    public void addEdge(Vertex source, Vertex destination, boolean bidirection) {
        if (!vertexMap.containsKey(source))
            addVertex(source);

        if (!vertexMap.containsKey(destination))
            addVertex(destination);

        vertexMap.get(source.data).neighbourList.add(destination);
        if(bidirection) {
            vertexMap.get(destination.data).neighbourList.add(source);
        }
    }

    /**
     * bfs for a graph is same as Level order traversal for a Tree. Unlike Tree graphs can have cycles.
     * To avoid processing the node again, we set the Visited flag for a vertex to true
     *
     * Water supply problem using bfs -- https://www.geeksforgeeks.org/implementing-water-supply-problem-using-breadth-first-search/?ref=rp
     */
    public void bfs(Vertex root) {
        System.out.println("\nBreath First");
        if (root == null) {
            return;
        }

        Queue<Vertex> queue = new LinkedList<>();
        root.isVisited = true;
        queue.add(root);

        while(!queue.isEmpty()) {
            Vertex curr = queue.remove();
            System.out.print(curr.data + " ");

            for(Vertex neighbour: curr.neighbourList) {
                if(!neighbour.isVisited) {
                    neighbour.isVisited = true;
                    queue.add(neighbour);
                }
            }
        }
        return;
    }

    /**
     * dfs is similar to Depth First Traversal of a Tree
     * The algorithm starts at the root node and explores as far as possible along each branch before backtracking.
     */
    public void dfs(Vertex root) {
        System.out.println("\nDepth First");
        if (root == null) {
            return;
        }

        Stack<Vertex> stack = new Stack<>();
        root.isVisited = true;
        System.out.print(root.data + " ");
        stack.push(root);

        while(!stack.isEmpty()) {
            Vertex next = getAdjacentUnvisited(stack.peek());

            if (next == null) {
                stack.pop();
            } else {
                next.isVisited = true;
                System.out.print(next.data + " ");
                stack.push(next);
            }
        }
    }

    // return an unVisited node from the neighbour list
    public Vertex getAdjacentUnvisited(Vertex vertex) {
        for (Vertex v : vertex.neighbourList) {
            if (!v.isVisited) {
                return v;
            }
        }
        return null;
    }

    public void dfsRecursive(Vertex root) {
        if (root == null) {
            return;
        }
        root.isVisited = true;
        System.out.print(root.data + " ");

        for (Vertex neighour: root.neighbourList) {
            if(!neighour.isVisited) {
                dfsRecursive(neighour);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);
        Vertex vertex4 = new Vertex(4);
        Vertex vertex5 = new Vertex(5);
        Vertex vertex6 = new Vertex(6);
        Vertex vertex7 = new Vertex(7);

        graph.addEdge(vertex1, vertex3, true);
        graph.addEdge(vertex1, vertex5, true);
        graph.addEdge(vertex2, vertex4, true);
        graph.addEdge(vertex2, vertex3, true);
        graph.addEdge(vertex4, vertex6, true);
        graph.addEdge(vertex3, vertex7, true);

        graph.bfs(vertex1);
        graph.cleanUp();
        graph.dfs(vertex1);
        graph.cleanUp();
        System.out.println("\nDepth First Recursive");
        graph.dfsRecursive(vertex1);
    }
}
