package problem.geeks;

/**
 * https://www.geeksforgeeks.org/cycles-of-length-n-in-an-undirected-and-connected-graph/
 *
 * Using DFS we find every possible path of length (n-1) for a particular source (or starting point). Then we check if this path
 * ends with the vertex it started with, if yes then we count this as the cycle of length n.
 * Notice that we looked for path of length (n-1) because the nth edge will be the closing edge of cycle.
 *
 * Every possible path of length (n-1) can be searched using only V – (n – 1) vertices (where V is the total number of vertices).
 * For above example, all the cycles of length 4 can be searched using only 5-(4-1) = 2 vertices. The reason behind this is quite simple,
 * because we search for all possible path of length (n-1) = 3 using these 2 vertices which include the remaining 3 vertices.
 * So, these 2 vertices cover the cycles of remaining 3 vertices as well, and using only 3 vertices we can’t form a cycle of length 4 anyways.
 *
 * One more thing to notice is that, every vertex finds 2 duplicate cycles for every cycle that it forms.
 * For above example 0th vertex finds two duplicate cycle namely 0 -> 3 -> 2 -> 1 -> 0 and 0 -> 1 -> 2 -> 3 -> 0.
 * Hence the total count must be divided by 2 because every cycle is counted twice.
 */
public class TriangleInGraph {
    // Number of vertices
    public static final int V = 5;
    static int count = 0;

    // Count cycles of length N in an undirected and connected graph.
    static int countCycles(int graph[][], int n) {
        // all vertex are marked un-visited initially.
        boolean marked[] = new boolean[V];

        // Searching for cycle by using v-n+1 vertices
        for (int i = 0; i < V - (n - 1); i++) {
            DFS(graph, marked, n-1, i, i);
            // ith vertex is marked as visited and will not be visited again
            marked[i] = true;
        }
        return count / 2;
    }

    static void DFS(int graph[][], boolean marked[], int n, int vert, int start) {
        // mark the vertex vert as visited
        marked[vert] = true;

        // if the path of length (n-1) is found
        if (n == 0) {
            // mark vert as un-visited to make it usable again
            marked[vert] = false;
            // Check if vertex vert end with vertex start
            if (graph[vert][start] == 1) {
                count++;
            }
            return;
        }

        // For searching every possible path of length (n-1)
        for (int i = 0; i < V; i++)
            if (!marked[i] && graph[vert][i] == 1)
                // DFS for searching path by decreasing length by 1
                DFS(graph, marked, n-1, i, start);

        // marking vert as unvisited to make it usable again
        marked[vert] = false;
    }

    public static void main(String[] args) {
        int graph[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0}};

        int n = 3;
        System.out.println("Total cycles of length "+ n + " are "+ countCycles(graph, n));
    }
}
