package com.algos.graphs.topological.sort;

/**
 * Created by Vijay on 5/12/16.
 */
public class App {
    public static void main(String[] args) {
        Vertex vertex0 = new Vertex("A");
        Vertex vertex1 = new Vertex("B");
        Vertex vertex2 = new Vertex("C");
        Vertex vertex3 = new Vertex("D");

        vertex0.addNeighbor(vertex1);
        vertex0.addNeighbor(vertex2);

        vertex1.addNeighbor(vertex3);

        TopologicalOrdering topologicalOrdering = new TopologicalOrdering();
        topologicalOrdering.dfs(vertex0);

        for (Vertex vertex : topologicalOrdering.getStack()) {
            System.out.println(vertex);
        }
    }
}
