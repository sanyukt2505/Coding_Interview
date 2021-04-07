package com.algos.graphs.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    /**
     * The bfs algorithms uses Queue interface from java.util package.
     * Assign value of type LinkedList to Queue reference variable because it implements Queue
     * @param root
     */
    public void bfs(Vertex root) {
        Queue<Vertex> queue = new LinkedList<>();

        // set root to as visited
        root.setVisited(true);
        // Add it to the queue
        queue.add(root);

        while (!queue.isEmpty()) {
            Vertex actualVertex = queue.remove();
            System.out.print(actualVertex + " ");

            for (Vertex v : actualVertex.getNeighborList()) {
                if (!v.isVisited()) {
                    v.setVisited(true);
                    queue.add(v);
                }
            }
        }
    }

}
