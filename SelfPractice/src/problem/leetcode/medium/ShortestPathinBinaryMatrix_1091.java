package problem.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * In an N by N square grid, each cell is either empty (0) or blocked (1). Can travel in 8 directions
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 */
public class ShortestPathinBinaryMatrix_1091 {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        if (grid[0][0] == 1 || grid[r - 1][c - 1] == 1) {
            return -1;
        }

        int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};

        /** slots with one cannot be traversed, so mark all the slots with 0 with a high value of distance */
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        grid[0][0] = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            /** traverse to each index and calculate the distance to get there */
            for (int i = 0; i < dirs.length; i++) {
                int newR = x + dirs[i][0];
                int newC = y + dirs[i][1];

                if (newR < 0 || newR >= r || newC < 0 || newC >= c || grid[newR][newC] == 1)
                    continue;

                grid[newR][newC] = Math.min(grid[newR][newC], grid[x][y]+1);
                q.add(new int[]{newR, newC});
            }
        }
        return grid[r-1][c-1] == Integer.MAX_VALUE ? -1 : grid[r-1][c-1];
    }
}
