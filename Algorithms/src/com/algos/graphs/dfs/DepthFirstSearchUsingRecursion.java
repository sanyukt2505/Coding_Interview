package com.algos.graphs.dfs;

public class DepthFirstSearchUsingRecursion {

    public void dfs(Vertex vertex) {
        System.out.println(vertex + " ");

        for (Vertex v : vertex.getAdjacenciesList()) {
            if (!v.isVisited()) {
                v.setVisited(true);
                v.setPredecessor(vertex);
                dfs(v);
            }
        }
    }
}
