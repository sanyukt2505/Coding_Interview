package com.algos.graphs.maze.solver;

public class MazeSolver {
    private int[][] mazeMap;
    private boolean[][] visited;
    private int startPositionCol;
    private int startPositionRow;

    public MazeSolver(int startPositionCol, int[][] mazeMap, int startPositionRow) {
        this.startPositionCol = startPositionCol;
        this.visited = new boolean[mazeMap.length][mazeMap.length];
        this.mazeMap = mazeMap;
        this.startPositionRow = startPositionRow;
    }

    public void findWayOut() {
        try {
            dfs(startPositionRow, startPositionCol+1);
            System.out.println("No solution found...");
        } catch (RuntimeException e) {
            System.out.println("Route found to exit!!!");
        }
    }

    private void dfs(int i, int j) {
        System.out.println("Visiting i = " + i +", j = "+j);

        if (mazeMap[i][j] == 3) {
            throw new RuntimeException();
        }

        int endOfMap = this.mazeMap.length - 1;

        if (visited[i][j]) {
            return;
        } else if (i < 0 || i >= endOfMap) {
            return;
        } else if (j < 0 || j >= endOfMap) {
            return;
        } else if (this.mazeMap[i][j] == 1) {
            return;
        } else {
            this.visited[i][j] = true;

            dfs(i+1, j); // down
            dfs(i, j+1); // right
            dfs(i, j-1); // left
            dfs(i-1, j); // up
        }
    }
}
