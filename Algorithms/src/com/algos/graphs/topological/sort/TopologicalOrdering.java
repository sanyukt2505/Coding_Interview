package com.algos.graphs.topological.sort;

import java.util.Stack;

/**
 * Topological Ordering can be performed only on Directed Acyclic Graphs.
 * It is important that there is no cycle in the graph. If cycle exists, then there won't be any Topological ordering.
 * Real world applications of topological sort: https://www.quora.com/What-are-some-real-world-applications-of-topological-sort
 */
public class TopologicalOrdering {
    private Stack<Vertex> stack;

    public TopologicalOrdering() {
        this.stack = new Stack<>();
    }

    public void dfs(Vertex vertex) {
        vertex.setVisited(true);

        for (Vertex v : vertex.getAdjacenciesList()) {
            if (!v.isVisited()) {
                v.setVisited(true);
                dfs(v);
            }
        }

        stack.push(vertex);
    }

    public Stack<Vertex> getStack() {
        return stack;
    }
}
