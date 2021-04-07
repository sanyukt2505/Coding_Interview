package com.algos.graphs.cycle.detection;

import java.util.List;

public class CycleDetection {

    public void detectCycles(List<Vertex> vertexList) {
        for (Vertex vertex : vertexList) {
            if (!vertex.isVisited()) {
                dfs(vertex);
            }
        }
    }

    public void dfs(Vertex vertex) {
        vertex.setVisited(false);
        vertex.setBeingVisited(true);

        for (Vertex v : vertex.getAdjacenciesList()) {
            // Graph theory
            if (v.isBeingVisited()) {
                System.out.println("Cycle is detected");
                return;
            }
            if (!v.isVisited()) {
                v.setVisited(true);
                dfs(v);
            }
        }

        vertex.setBeingVisited(false);
        vertex.setVisited(true);
    }
}
