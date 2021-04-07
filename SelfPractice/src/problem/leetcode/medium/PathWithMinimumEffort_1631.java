package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/path-with-minimum-effort/
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
 * where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
 * and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
 * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 */
public class PathWithMinimumEffort_1631 {
    public int minimumEffortPath(int[][] heights) {
        return backtrack(0, 0, heights, heights.length, heights[0].length, 0);
    }

    int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int maxSoFar = Integer.MAX_VALUE;

    /** dfs and keep updating the maxDiff*/
    int backtrack(int x, int y, int[][] heights, int row, int col, int maxDiff) {
        if (x == row - 1 && y == col - 1) {
            maxSoFar = Math.min(maxSoFar, maxDiff);
            return maxDiff;
        }
        int currentHeight = heights[x][y];
        heights[x][y] = 0;
        /** minEffort is the minimum effort to get to current position [x,y] */
        int minEffort = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];

            if (isValidCell(nextX, nextY, row, col) && heights[nextX][nextY] != 0) {
                int currDiff = Math.abs(heights[nextX][nextY] - currentHeight);
                int maxCurrDiff = Math.max(maxDiff, currDiff);

                /** if we have already found a path to reach the destination cell with maxSoFar, then, we would only
                 * explore other paths if it takes efforts less than maxSoFar */
                if (maxCurrDiff < maxSoFar) {
                    int result = backtrack(nextX, nextY, heights, row, col, maxCurrDiff);
                    minEffort = Math.min(minEffort, result);
                }
            }
        }
        heights[x][y] = currentHeight;
        return minEffort;
    }

    boolean isValidCell(int x, int y, int row, int col) {
        return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
    }

}
