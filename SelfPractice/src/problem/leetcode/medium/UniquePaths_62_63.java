package problem.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/unique-paths/
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
 * corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * Input: m = 3, n = 7      Output: 28
 */
public class UniquePaths_62_63 {
    /** Recursive */
    public int uniquePathsRecursive(int m, int n) {
        /** to reach top row of left column - there is only one way */
        if (m == 1 || n == 1) {
            return 1;
        }
        return uniquePathsRecursive(m - 1, n) + uniquePathsRecursive(m, n - 1);
    }

    /**  DP way
     * Initiate 2D array d[m][n] = number of paths.
     * To start, put number of paths equal to 1 for the first row and the first column.
     * Iterate over all "inner" cells: d[col][row] = d[col - 1][row] + d[col][row - 1].
     * Return d[m - 1][n - 1].
     */
    public int uniquePaths(int m, int n) {
        int[][] d = new int[m][n];

        for(int[] arr : d) {
            Arrays.fill(arr, 1);
        }
        for(int col = 1; col < m; ++col) {
            for(int row = 1; row < n; ++row) {
                d[col][row] = d[col - 1][row] + d[col][row - 1];
            }
        }
        return d[m - 1][n - 1];
    }

    /** https://leetcode.com/problems/unique-paths-ii/
     * Unique ways , if the matrix have Obstacles
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        /** If the starting cell has an obstacle, then simply return as there would be no paths to the destination */
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        /** Number of ways of reaching the starting cell = 1 */
        obstacleGrid[0][0] = 1;
        /** Filling the values for the first column */
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }
        /** Filling the values for the first row  */
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        /** Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]  */
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }
}
