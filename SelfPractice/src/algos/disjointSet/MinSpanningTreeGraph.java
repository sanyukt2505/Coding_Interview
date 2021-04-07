package algos.disjointSet;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
 * A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.
 * 1. Sort all the edges in non-decreasing order of their weight.
 * 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it.
 * 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
 *
 * Applications:
 *      - network design - telephone, electrical, hydraulic, TV cable, computer, road
 *      - Approximation algorithms for NP-hard problems - Traveling salesperson problem, Steiner tree
 *      - Indirect applications.
 *          -- max bottleneck paths
 *          -- LDPC codes for error correction
 *          -- image registration with Renyi entropy
 *          -- learning salient features for real-time face verification
 *          -- reducing data storage in sequencing amino acids in a protein
 *          -- model locality of particle interactions in turbulent fluid flows
 *          -- autoconfig protocol for Ethernet bridging to avoid cycles in a network
 */
class MinSpanningTreeGraph {

    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public int compareTo(Edge comparedEdge) {
            return this.weight - comparedEdge.weight;
        }
    }

    int numV, numE;
    Edge edge[];    // list of all edges
    int[] rank, parent;

    // Creates a graph with V vertices and E edges
    public MinSpanningTreeGraph(int numV, int numE) {
        this.numV = numV;
        this.numE = numE;
        this.edge = new Edge[numE];

        for (int i=0; i < numE; ++i)
            edge[i] = new Edge();
    }

    /**
     * A utility function to find set of an element key (uses path compression technique)
     *     find root and make root as parent of i (path compression)
     */
    int find(int parent[], int i) {
        if(parent[i] != i)
            parent[i] = find(parent, parent[i]);

        return parent[i];
    }

    /**
     * A function that does union of two sets of x and y (uses union by rank)
     *      - Attach smaller rank tree under root of high rank tree
     *      - If ranks are same, then make one as root and increment its rank by one
     */
    void union(int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);

        if(rank[xRoot] < rank[yRoot])
            parent[xRoot] = yRoot;
        else if(rank[xRoot] > rank[yRoot])
            parent[yRoot] = xRoot;
        else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    void kruskalMinSpanningTree() {
        Edge result[] = new Edge[numV];
        parent = new int[numV];
        rank = new int[numV];
        int edgeIndex = 0;

        // Step 1:  Sort all the edges in non-decreasing order of their weight.
        //    If we are not allowed to change the given graph, we can create a copy of array of edges
        Arrays.sort(edge);

        for(int i=0; i<numV ; ++i) {
            parent[i] = i;
            rank[i] = 0;
        }

        int next = 0;
        // Number of edges to be taken is equal to V-1
        while (edgeIndex < numV - 1) {
            // Step 2: Pick the smallest edge
            Edge nextEdge = edge[next++];

            int x = find(parent, nextEdge.src);
            int y = find(parent, nextEdge.dest);

            // If including this edge does't cause cycle, include it in result and increment the index of result
            // Else discard the next_edge
            if (x != y) {
                result[edgeIndex++] = nextEdge;
                union(x, y);
            }
        }

        //printing the MST
        System.out.println("Edges in MST");
        for (int i=0; i < edgeIndex; ++i)
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
    }

    public static void main (String[] args)
    {

        /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
        int V = 4;  // Number of vertices in graph
        int E = 5;  // Number of edges in graph
        MinSpanningTreeGraph graph = new MinSpanningTreeGraph(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;

        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;

        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;

        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;

        // add edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        graph.kruskalMinSpanningTree();
    }
}
