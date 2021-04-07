package problem.leetcode.hard;

/**
 * https://leetcode.com/problems/sudoku-solver/
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 */
public class SudokuSolver_37 {
    /**
     * Put a number on the board
     * Validate row, column, and 3x3 subgrid to perform validation.
     * If it breaks the board, BACKTRACK
     */
    public void solveSudoku(char[][] board) {
        solveSudoku(board, 0, 0);
    }

    public boolean solveSudoku(char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) { //go through all the options
                        if (isValid(board, c, i, j)) {
                            board[i][j] = c;
                            /** Recursive call solveSudoku to solve rest of the board
                             *  it any thing fails, reset the current placement
                             *  exit TRUE, as soon as one combination return true */
                            if(solveSudoku(board, i, j))
                                return true;
                            else
                                board[i][j] = '.';
                        }
                    }
                    //because it was '.' we want to go through it once again
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int c, int row, int col) {
        for (int i = 0; i < 9; i++) {
            if(board[row][i] == c) return false;
            if(board[i][col] == c) return false;
            if(board[3*(row/3) + i/3][3*(col/3) + i%3] == c) return false;
        }

        return true;
    }
}
