package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/surrounded-regions/
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */
public class SurroundedRegions_130 {
    public void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;

        // First scan through the boundaries and if there is any 0
        // do a dfs on any such connected regions and convert them to "*"
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O')
                boundryDFS(board, i, 0);
            if (board[i][col-1] == 'O')
                boundryDFS(board, i, col-1);
        }

        for (int j = 0; j < col; j++) {
            if (board[0][j] == 'O')
                boundryDFS(board, 0, j);
            if (board[row-1][j] == 'O')
                boundryDFS(board, row-1, j);
        }

        // once the regions connected to boundary 'O' are marked accordingly
        // that means any other O, currently present in matrix is not connected to boundary
        // and can be changed to 'X'
        for (int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    // do a dfs on 'O' and chnage any connected O to *
    public void boundryDFS(char[][] board, int i, int j) {
        if (i < 0 || i > board.length-1 || j < 0 || j > board[0].length-1 || board[i][j] != 'O' )
            return;

        if (board[i][j] == 'O')
            board[i][j] = '*';

        boundryDFS(board, i-1, j);
        boundryDFS(board, i+1, j);
        boundryDFS(board, i, j-1);
        boundryDFS(board, i, j+1);
        return;
    }
}
