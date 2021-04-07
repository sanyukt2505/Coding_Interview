package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-height-trees/
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates
 * that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree
 * as the root. When you select a node x as the root, the result tree has height h.
 * Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]        Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 */
public class MinimumHeightTrees_310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        /** base cases - only one centre node */
        if (n < 2) {
            ArrayList<Integer> centroids = new ArrayList<>();
            for (int i = 0; i < n; i++)
                centroids.add(i);
            return centroids;
        }

        /** Build the graph with the adjacency list  */
        ArrayList<Set<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < n; i++)
            neighbors.add(new HashSet<>());

        for (int[] edge : edges) {
            Integer start = edge[0], end = edge[1];
            neighbors.get(start).add(end);
            neighbors.get(end).add(start);
        }

        /** Initialize the first layer of leaves   */
        ArrayList<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (neighbors.get(i).size() == 1)
                leaves.add(i);

        /** Trim the leaves until reaching the centroids  */
        int remainingNodes = n;
        while (remainingNodes > 2) {
            remainingNodes -= leaves.size();
            ArrayList<Integer> newLeaves = new ArrayList<>();

            /** remove the current leaves along with the edges  */
            for (Integer leaf : leaves) {
                /** the only neighbor left for the leaf node  */
                Integer neighbor = neighbors.get(leaf).iterator().next();
                /** remove the edge along with the leaf node   */
                neighbors.get(neighbor).remove(leaf);
                if (neighbors.get(neighbor).size() == 1)
                    newLeaves.add(neighbor);
            }
            // prepare for the next round
            leaves = newLeaves;
        }
        // The remaining nodes are the centroids of the graph
        return leaves;
    }
}
