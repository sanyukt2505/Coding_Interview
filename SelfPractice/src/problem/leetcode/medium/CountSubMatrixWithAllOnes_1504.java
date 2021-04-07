package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/count-submatrices-with-all-ones/
 * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
 * Input: mat = [[1,0,1],
 *               [1,1,0],
 *               [1,1,0]]
 * Output: 13
 * Time: O(n^3)
 */
public class CountSubMatrixWithAllOnes_1504 {
    public int numSubmat(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;

        /** 1. Find width of each element of a row from right to left. basically count continuous 1s in each row
         * dp for above solution will look like
         *  1 0 1
         *  2 1 0
         *  2 1 0       */
        for(int r = 0; r < row; r++){
            for(int c = col-2; c >= 0; c--){
                if(mat[r][c] == 1){
                    mat[r][c] += mat[r][c+1];
                }
            }
        }

        int count = 0;
        /** For each index, calculate num of rectangles you can create for each height option */
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){

                int min_width = mat[r][c];
                for(int h = r; h < row; h++){
                    if(mat[h][c] == 0) {                  /** no need to do that for any 0s */
                        break;
                    }

                    /** one can create a rectangle of the min_width across multiple rows*/
                    min_width = Math.min(min_width, mat[h][c]);
                    count += min_width;
                }
            }
        }
        return count;

    }
}
