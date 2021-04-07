package problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/sparse-matrix-multiplication/
 * Given two sparse matrices A and B, return the result of AB.
 *
 * You may assume that A's column number is equal to B's row number.
 */
public class SparseMatrixMultiplication_311 {

    public int[][] multiplyMatrixSimple(int[][] A, int[][] B) {
         if (A == null || B == null)
             return null;

         int[][] res = new int[A.length][B.length];

         for(int r = 0; r < res.length; r++) {
             for(int c = 0; c< res[0].length; c++) {
                 int sum = 0;
                 for(int len = 0; len < A[0].length; len++) {
                     sum += A[r][len] * B[len][c];
                 }
                 res[r][c] = sum;
             }
         }
         return res;
    }

    /**
     * Sparse matrix is a matrix that has a lot of 0s in it
     * - regular matrix multiply = O(nmk) where A= n X m and B = m X k
     * - But for the sparse matrix, we can reduce the complexity to some extent.
     * C[i][j] will be update only when A[i][k] * B[k][j] != 0, which means any of A[i][k] and B[k][j] should not be 0
     */
    public int[][] multiplySparseMatrix(int[][] A, int[][] B) {
        /** co-ordinate list for each matrix - to store the co-ordinate of non-zero elements  */
        Map<Integer, Map<Integer, Integer>> amap = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> bmap = new HashMap<>();

        int acol = A[0].length;
        int arow = A.length;
        int bcol = B[0].length;
        int brow = B.length;

        int[][] res = new int[arow][bcol];

        // construct the map for A
        // map: row index, map<column index, value>
        for(int i = 0; i < arow; i++) {
            amap.put(i, new HashMap<>());
            for(int j = 0; j < acol; j++){
                if(A[i][j] != 0) {
                    amap.get(i).put(j, A[i][j]);
                }
            }
        }

        // construct the map for B -- create transpose of the matrix
        // map: column index, map<row index, value>
        for(int i = 0; i < bcol; i++){
            bmap.put(i, new HashMap<>());
            for(int j = 0; j < brow; j++) {
                if(B[j][i] != 0) {
                    bmap.get(i).put(j, B[j][i]);
                }
            }
        }

        /** C[i][j] = A[i][k] * B[k][j]
         *  if D = tranpose(B), then    C[i][j] = A[i][k] * D[j][k]     */
        for(int row: amap.keySet()) {           // get only valid row from A
            for(int col: bmap.keySet()) {       // get only valid column from B
                int sum = 0;
                // calculate result[row][col]: aCol0*bRow0 + aCol1*bRow1 .. + aColn*bRown. Just check none zero values.
                for(int aCol: amap.get(row).keySet()) {
                    if(bmap.get(col).containsKey(aCol))
                        sum += amap.get(row).get(aCol) * bmap.get(col).get(aCol);
                }
                res[row][col] = sum;
            }
        }

        return res;

    }

}
