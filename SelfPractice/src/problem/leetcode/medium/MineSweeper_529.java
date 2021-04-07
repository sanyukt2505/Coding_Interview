package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minesweeper/
 * You are given a 2D char matrix representing the game board.
 *  'M' represents an unrevealed mine,
 *  'E' represents an unrevealed empty square,
 *  'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals)
 *      mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square,
 *  'X' represents a revealed mine.
 *
 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'),
 * return the board after revealing this position according to the following rules:
 *      - If a mine ('M') is revealed, then the game is over - change it to 'X'.
 *      - If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all
 *          of its adjacent unrevealed squares should be revealed recursively.
 *      - If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8')
 *          representing the number of adjacent mines.
 *      - Return the board when no more squares will be revealed.
 */
public class MineSweeper_529 {
    private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public char[][] updateBoardDFS(char[][] board, int[] click) {
        dfs(board, click);
        return board;
    }

    public void dfs(char[][] board, int[] click) {
        int r = click[0], c = click[1];
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return;
        }
        if (board[r][c] != 'E')
            return;
        board[r][c] = getMineCount(r, c, board);
        if (board[r][c] == 'B') {
            for (int[] next : getNextPos(r, c, board)) {
                dfs(board, next);
            }
        }
    }

    private char getMineCount(int r, int c, char[][] board) {
        int mineCount = 0;
        for (int[] next : getNextPos(r, c, board)) {
            if (board[next[0]][next[1]] == 'M') mineCount++;
        }
        return (mineCount == 0) ? 'B' : (char) ('0' + mineCount);
    }

    private List<int[]> getNextPos(int r, int c, char[][] board) {
        List<int[]> ret = new ArrayList<>();
        for (int[] dir : directions) {
            int nr = r + dir[0], nc = c + dir[1];
            if (isValidIndex(nr, nc, board))
                ret.add(new int[]{nr, nc});
        }
        return ret;
    }

    private boolean isValidIndex(int r, int c, char[][] board) {
        return (r >= 0 && r < board.length && c >= 0 && c < board[0].length);
    }

//    ----------------  BFS implementation ---------------------
    public char[][] updateBoardBFS(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(click);
        while (!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0], y = xy[1];
            if (board[x][y] != 'E')
                continue;
            board[x][y] = getMineCount(x, y, board);
            if (board[x][y] == 'B') {
                for (int[] next : getNextPos(x, y, board)) {
                    q.offer(next);
                }
            }
        }
        return board;
    }

}
