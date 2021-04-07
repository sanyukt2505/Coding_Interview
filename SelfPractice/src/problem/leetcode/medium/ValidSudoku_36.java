package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/valid-sudoku/
 * Determine if a 9 x 9 Sudoku board is valid
 */

import java.util.HashSet;

public class ValidSudoku_36 {
    /**
     * Using Array for rows, column and box
     * whenever you get a number -- n , add it to row[i][n], col[j][n], box[index][n]
     * if that place is already occupied return a false
     */
    public boolean isValidSudokuUsingArray(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.'){
                    int n = Character.getNumericValue(board[i][j]);
                    int boxindex = (i / 3) * 3 + j / 3;
                    if(rows[i][n-1] ||
                            columns[j][n-1] ||
                            boxes[boxindex][n-1])
                        return false;
                    else{
                        rows[i][n-1] = true;
                        columns[j][n-1] = true;
                        boxes[boxindex][n-1] = true;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Using one Set<String>
     * Keep every element add an entry saying "Found at row i", "Found at col j", "Found at Box"
     * if any of the set.add() return false, that means its already present
     */
    public boolean isValidSudokuOneHashSet(char[][] board) {
        HashSet<String> seen = new HashSet();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                char curr = board[i][j];
                int boxIndex = (i / 3) * 3 + j / 3;
                if (curr != '.') {
                    // set.add has a return value -- true = when success added, false = when already present
                    if (!seen.add(curr + " found in row " + i) ||
                            !seen.add(curr + " found in col " + j) ||
                            !seen.add(curr + " found in box " + boxIndex)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Using HashSet for rows, column and box
     */
    public boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < 9; i++) {
            HashSet<Character> rows = new HashSet();
            HashSet<Character> cols = new HashSet();
            HashSet<Character> box = new HashSet();

            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.' && !rows.add(board[i][j]))
                    return false;
                if (board[j][i] != '.' && !cols.add(board[j][i]))
                    return false;

                int boxRow = 3*(i/3);
                int boxCol = 3*(i%3);
                if (board[boxRow + j/3][boxCol + j%3] != '.' && !box.add(board[boxRow + j/3][boxCol + j%3]))
                    return false;
            }
        }
        return true;
    }
}
