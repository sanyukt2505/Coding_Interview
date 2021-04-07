package com.algos.graphs.dfs;

public class DFSTest {
    public static void main(String[] args) {
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");
        Vertex vertexD = new Vertex("D");
        Vertex vertexE = new Vertex("E");

        vertexA.addNeighbor(vertexB);
        vertexA.addNeighbor(vertexC);

        vertexB.addNeighbor(vertexD);
        vertexB.addNeighbor(vertexE);

        DepthFirstSearchUsingStack depthFirstSearchUsingStack = new DepthFirstSearchUsingStack();
        depthFirstSearchUsingStack.dfs(vertexA);

        System.out.println();

        DepthFirstSearchUsingRecursion depthFirstSearchUsingRecursion = new DepthFirstSearchUsingRecursion();
        depthFirstSearchUsingRecursion.dfs(vertexA);
    }
}
