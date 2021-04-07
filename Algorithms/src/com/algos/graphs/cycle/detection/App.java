package com.algos.graphs.cycle.detection;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Vertex vertex0 = new Vertex("A");
        Vertex vertex1 = new Vertex("B");
        Vertex vertex2 = new Vertex("C");

        List<Vertex> vertexList = new ArrayList<>();
        vertexList.add(vertex0);
        vertexList.add(vertex1);
        vertexList.add(vertex2);

        vertex0.addNeighbor(vertex1);
        vertex0.addNeighbor(vertex2);
        // create a cycle
        vertex2.addNeighbor(vertex0);

        CycleDetection cycleDetection = new CycleDetection();
        cycleDetection.detectCycles(vertexList);
    }
}
