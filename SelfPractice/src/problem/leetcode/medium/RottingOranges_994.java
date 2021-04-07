package problem.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 * In a given grid, each cell can have one of three values:
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 */
public class RottingOranges_994 {
    /**  BFS
     * One of the most distinguished code patterns in BFS algorithms is that often we use a queue data structure to
     * keep track of the candidates that we need to visit during the process.
     * The main algorithm is built around a loop iterating through the queue. At each iteration, we pop out an
     * element from the head of the queue. Then we do some particular process with the popped element.
     * More importantly, we then append neighbors of the popped element into the queue, to keep the BFS process running.
     */
    public int orangesRotting(int[][] grid) {
        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        Queue<String> queue = new LinkedList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        int freshOrange = 0;
        int minutes = -1;

        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if (grid[i][j] == 2)
                    queue.add("" + i + j);
                else if(grid[i][j] == 1)
                    freshOrange++;
            }
        }

        // delimiter to mark end of one loop of handling all rotting oranges
        queue.add("-");

        while (!queue.isEmpty()) {
            String entry = queue.poll();
            if (entry.equals("-")) {
                minutes++;
                if (!queue.isEmpty())
                    queue.add("-");
            } else {
                int r = entry.charAt(0) - '0';
                int c = entry.charAt(1) - '0';
                for(int[] dir: directions) {
                    int nextRow = r + dir[0];
                    int nextCol = c + dir[1];

                    if(nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                        if (grid[nextRow][nextCol] == 1) {
                            grid[nextRow][nextCol] = 2;
                            queue.add("" + nextRow + nextCol);
                            freshOrange--;
                        }
                    }
                }
            }
        }
        return freshOrange == 0 ? minutes : -1;
    }
}
