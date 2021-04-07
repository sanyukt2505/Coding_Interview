package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/design-tic-tac-toe/
 */
public class TicTacToeDesign_348 {
    private int n;
    private int[] rows;
    private int[] cols;
    private int[] diagonal; // either +ve or negative

    /** Initialize your data structure here. */
    public TicTacToeDesign_348(int n) {
        this.n = n;
        this.rows = new int[n];
        this.cols = new int[n];
        this.diagonal = new int[2];
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int playerVal = (player == 1) ? 1 : -1;

        rows[row] += playerVal;
        cols[col] += playerVal;

        if(row == col) {
            diagonal[0] += playerVal;
        }
        if( n - 1 - col == row) {
            diagonal[1] += playerVal;
        }

        if (rows[row] == n || cols[col] == n || diagonal[0] == n || diagonal[1] == n) {
            return 1;
        } else if (rows[row] == -n || cols[col] == -n || diagonal[0] == -n || diagonal[1] == -n) {
            return 2;
        } else {
            return 0;
        }
    }
}
