package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 *
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * Pacific   ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 *
 ***** Solution ******
 * For every point you have to check if the water can flow to both the ocean given the height.
 * We can start from edges and check if the A[r][c] > height, where height represents the height of the previously
 * visited point.
 */
public class PacificAtlanticWaterFlow_417 {
    public List<List<Integer>> pacificAtlantic(int[][] A) {
        List<List<Integer>> res = new ArrayList<>();

        if (A == null || A.length == 0)
            return res;

        int nr = A.length;
        int nc = A[0].length;

        // boolean array to keep track of points from where the water can flow to corresponding ocean
        boolean[][] pacific = new boolean[nr][nc];
        boolean[][] atlantic = new boolean[nr][nc];

        for (int c = 0; c < nc; c++) {
            dfs(A, pacific, 0, c, A[0][c]);
            dfs(A, atlantic, nr - 1, c, A[nr - 1][c]);
        }

        for (int r = 0; r < nr; r++) {
            dfs(A, pacific, r, 0, A[r][0]);
            dfs(A, atlantic, r, nc - 1, A[r][nc - 1]);
        }

        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (pacific[r][c] && atlantic[r][c])
                    res.add(Arrays.asList(r, c));
            }
        }
        return res;
    }

    public void dfs(int[][] A, boolean[][] flow, int r, int c, int height) {
        int nr = A.length;
        int nc = A[0].length;

        /** return if current index height is less than last index height */
        if (r < 0 || r >= nr || c < 0 || c >= nc || flow[r][c] || A[r][c] < height)
            return;

        flow[r][c] = true;

        dfs(A, flow, r + 1, c, A[r][c]);
        dfs(A, flow, r - 1, c, A[r][c]);
        dfs(A, flow, r, c + 1, A[r][c]);
        dfs(A, flow, r, c - 1, A[r][c]);
    }
}
