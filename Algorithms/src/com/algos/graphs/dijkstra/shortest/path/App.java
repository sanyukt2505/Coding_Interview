package com.algos.graphs.dijkstra.shortest.path;

public class App {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");

        v1.addNeighbor(new Edge(1, v1, v2));
        v1.addNeighbor(new Edge(10, v1, v3));
        v2.addNeighbor(new Edge(1, v2, v3));

        Algorithm algorithm = new Algorithm();
        algorithm.computePath(v1);

        System.out.println(algorithm.getShortestPathTo(v3));
    }
}
