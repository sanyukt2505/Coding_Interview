package com.algos.graphs.dijkstra.shortest.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Algorithm {

    public void computePath(Vertex sourceVertex) {
        // Initialize the minDistance from sourceVertex to itself to be 0
        sourceVertex.setMinDistance(0);

        // The heap based PriorityQueue data structure guarantees logN performance for insert and retrieval of elements
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();

        // Add the sourceVertex in the queue
        priorityQueue.add(sourceVertex);

        while (!priorityQueue.isEmpty()) {
            /**
             * The following code provides the Vertex which has minimum distance from the sourceVertex
             * Reason: Vertex class implements Comparable where compareTo method prioritises the next value
             */
            Vertex actualVertex = priorityQueue.poll();

            for (Edge edge : actualVertex.getAdjacenciesList()) {
                Vertex v = edge.getTargetVertex();
                double weight = edge.getWeight();

                // Calculate distance via the target vertex
                double minDistanceViaV = actualVertex.getMinDistance() + weight;

                /**
                 * If the new min distance via target vertex is less than target vertex's pre-calculated min distance:
                 * 1. remove the vertex v from priorityQueue (Since v's predecessor and minDistance need to be corrected)
                 * 2. Set the node through which the min distance is less to be previous vertex (or predecessor)
                 * 3. Set the minimum distance to the new min distance
                 * Finally, after vertex v's details are corrected, add vertex v back to priorityQueue
                 */
                if (minDistanceViaV < v.getMinDistance()) {
                    priorityQueue.remove(v);
                    v.setPreviousVertex(actualVertex);
                    v.setMinDistance(minDistanceViaV);
                    priorityQueue.add(v);
                }
            }
        }
    }

    /**
     * Using backtracking, traverse from targetVertex until previous vertex doesn't become null.
     * Each time, add the vertex in the way to the vertices list which is path to target vertex.
     * @param targetVertex
     * @return path to target vertex
     */
    public List<Vertex> getShortestPathTo(Vertex targetVertex) {
        List<Vertex> path = new ArrayList<>();

        for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPreviousVertex()) {
            path.add(vertex);
        }

        Collections.reverse(path);

        return path;
    }
}
