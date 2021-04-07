package problem.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/number-of-islands/
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid
 * are all surrounded by water.
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class NumberOfIslands_200 {

    /**
     * do a dfs whenever you get a 1
     * In DFS traverse up down, right and left  and for each step
     * mark visited = true  or change the original array to '0'
     */
    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visit = new boolean[row][col];
        int numOfIslands = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visit[i][j] && grid[i][j] == '1') {
                    numOfIslands++;
                    dfs(grid, visit, i, j);
                }
            }
        }
        return numOfIslands;
    }

    public void dfs(char[][] grid,boolean[][] visit, int row, int col) {
        if(row < 0 || row > grid.length || col < 0 || col > grid[0].length || grid[row][col] == '0')
            return;

        visit[row][col] = true;
        grid[row][col] = '0';
        dfs(grid, visit, row - 1, col);
        dfs(grid, visit, row + 1, col);
        dfs(grid, visit, row, col - 1);
        dfs(grid, visit, row, col + 1);
    }

    /**
     * BFS approach using Queue
     */
    private static class Pair {
        int x, y;

	public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[] row = { 1, -1, 0, 0};
    private static final int[] col = { 0, 0, 1, -1};

    public static void BFS(int[][] grid, boolean[][] visit, int i, int j)
    {
        // create an empty queue and enqueue source node
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(i, j));

        // mark source node as processed
        visit[i][j] = true;

        // loop till queue is empty
        while (!q.isEmpty())
        {
            // pop front node from queue and process it
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();

            // check for all 8 possible movements from current cell
            // and enqueue each valid movement
            for (int k = 0; k < 8; k++)
            {
                int newRow = x + row[k];
                int newCol = y + col[k];
                // Skip if location is invalid or already processed or has water
                if(newRow < 0 || newRow > grid.length || newCol < 0 || newCol > grid[0].length || grid[newRow][newCol] == '0')
                {
                    visit[newRow][newCol] = true;
                    q.add(new Pair(newRow, newCol));
                }
            }
        }
    }
}
