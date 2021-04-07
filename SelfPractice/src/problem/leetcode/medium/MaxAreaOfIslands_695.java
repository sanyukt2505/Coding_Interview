package problem.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/max-area-of-island/
 * https://www.youtube.com/watch?v=gqOlRTIA4oc&t=150s
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected
 * 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 */
public class MaxAreaOfIslands_695 {
    int[][] grid;
    boolean[][] seen;

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        int ans = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                ans = Math.max(ans, area(r, c));
            }
        }
        return ans;
    }

    public int area(int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length ||
                seen[r][c] || grid[r][c] == 0)
            return 0;
        seen[r][c] = true;
        return (1 + area(r+1, c) + area(r-1, c)
                + area(r, c-1) + area(r, c+1));
    }

    /** BFS approach */
    public int maxAreaOfIslandBFS(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visit = new boolean[row][col];
        int res = 0;

        for (int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if (!visit[i][j] && grid[i][j] == 1) {
                    int area = bfs(grid, visit, i, j);
                    res = Math.max(area, res);
                }
            }
        }
        return res;
    }

    public int bfs(int[][]grid, boolean[][] visit, int row, int col) {
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        int area = 0;
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(row, col));
        visit[row][col] = true;

        while(!q.isEmpty()) {
            Pair p = q.remove();
            area++;
            for (int i = 0; i < dr.length; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if(nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == 1 && !visit[nr][nc]) {
                    q.add(new Pair(nr, nc));
                    visit[nr][nc] = true;
                }
            }
        }
        return area;
    }

    class Pair {
        int r; int c;
        public Pair(int x, int y) {
            this.r = x;
            this.c = y;
        }
    }

    /**
     * DFS using Iterative approach, using Stack
     * @param grid
     * @return
     */
    public int maxAreaOfIslandIterative(int[][] grid) {
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        int ans = 0;
        for (int r0 = 0; r0 < grid.length; r0++) {
            for (int c0 = 0; c0 < grid[0].length; c0++) {
                if (grid[r0][c0] == 1 && !seen[r0][c0]) {
                    int shape = 0;
                    Stack<int[]> stack = new Stack();
                    stack.push(new int[]{r0, c0});
                    seen[r0][c0] = true;
                    while (!stack.empty()) {
                        int[] node = stack.pop();
                        int r = node[0], c = node[1];
                        shape++;
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (0 <= nr && nr < grid.length && 0 <= nc && nc < grid[0].length &&
                                    grid[nr][nc] == 1 && !seen[nr][nc]) {
                                stack.push(new int[]{nr, nc});
                                seen[nr][nc] = true;
                            }
                        }
                    }
                    ans = Math.max(ans, shape);
                }
            }
        }
        return ans;
    }
}
