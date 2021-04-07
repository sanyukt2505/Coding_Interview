package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle
 * defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Given matrix = [ [3, 0, 1, 4, 2],
 *                  [5, 6, 3, 2, 1],
 *                  [1, 2, 0, 1, 5],
 *                  [4, 1, 0, 1, 7],
 *                  [1, 0, 3, 0, 5]]
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 */
public class RectangeSum2DArray_304 {

    /** Try to see the 2D matrix as mm rows of 1D arrays.
     * To find the region sum, we just accumulate the sum in the region row by row.
     * [3, 0, 1, 4, 2] in original array convert to sumArr  [0, 3, 3, 4, 8, 10]  */
    private int[][] sumArr;

    public RectangeSum2DArray_304(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return;

        sumArr = new int[matrix.length][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                /** Cumulative sum of each row  */
                sumArr[r][c + 1] = sumArr[r][c] + matrix[r][c];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        /** adding the cumulative sum of each row - subtracting the sum of starting col */
        for (int row = row1; row <= row2; row++) {
            sum += sumArr[row][col2 + 1] - sumArr[row][col1];
        }
        return sum;
    }

}
