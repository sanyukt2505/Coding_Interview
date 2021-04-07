package problem.geeks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 *
 * To detect a back edge, keep track of vertices currently in the recursion stack of function for DFS traversal.
 * If a vertex is reached that is already in the recursion stack, then there is a cycle in the tree.
 * The edge that connects the current vertex to the vertex in the recursion stack is a back edge.
 * Use recStack[] array to keep track of vertices in the recursion stack.
 */
public class CyclesInDirectedGraph {
    private final int V;
    private final List<List<Integer>> adj;

    public CyclesInDirectedGraph(int V)
    {
        this.V = V;
        adj = new ArrayList<>(V);

        for (int i = 0; i < V; i++)
            adj.add(new LinkedList<>());
    }

    public void addEdge(int source, int dest) {
        adj.get(source).add(dest);
    }

    public boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack)
    {
        // Mark the current node as visited and part of recursion stack
        if (recStack[i])
            return true;

        if (visited[i])
            return false;

        visited[i] = true;

        recStack[i] = true;
        List<Integer> neighbour = adj.get(i);

        for (Integer c: neighbour)
            if (isCyclicUtil(c, visited, recStack))
                return true;

        recStack[i] = false;

        return false;
    }

    // Returns true if the graph contains a cycle, else false.
    public boolean isCyclic()
    {
        // Mark all the vertices as not visited and not part of recursion stack
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        // Call the recursive decode function to detect cycle in different DFS trees
        for (int i = 0; i < V; i++)
            if (isCyclicUtil(i, visited, recStack))
                return true;

        return false;
    }

    // Driver code
    public static void main(String[] args) {
        CyclesInDirectedGraph graph = new CyclesInDirectedGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        if (graph.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't " + "contain cycle");
    }
}
