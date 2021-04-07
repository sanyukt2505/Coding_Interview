package com.algos.graphs.dfs;

import java.util.Stack;

public class DepthFirstSearchUsingStack {
    private Stack<Vertex> stack;

    public DepthFirstSearchUsingStack() {
        this.stack = new Stack<>();
    }

    public void dfs(Vertex root) {
        stack.add(root);
        root.setVisited(true);

        while (!stack.isEmpty()) {
            Vertex actualVertex = stack.pop();
            System.out.print(actualVertex + " ");

            for (Vertex v : actualVertex.getAdjacenciesList()) {
                if (!v.isVisited()) {
                    v.setVisited(true);
                    v.setPredecessor(actualVertex);
                    stack.add(v);
                }
            }
        }
    }
}
