package problem.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/diagonal-traverse/
 * https://www.geeksforgeeks.org/print-matrix-diagonal-pattern/?ref=lbp
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 * Input:[  [ 1, 2, 3 ],
 *          [ 4, 5, 6 ],
 *          [ 7, 8, 9 ]]
 *
 * Output:  [1,2,4,7,5,3,6,8,9]
 */
public class DiagonalTraverse_498 {

    public static int[] findDiagonalOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return new int[0];
        int row = matrix.length;
        int col = matrix[0].length;

        int[] result=new int[row*col];
        result[0] = matrix[0][0];//Initialization start position
        // i is row, j is column and k is result index
        int i = 0, j = 0, k = 1;

        while(k < col * row){
            //move up-right first
            while(i >= 1 && j < col-1){
                i--;
                j++;
                result[k++] = matrix[i][j];
            }
            //when we can't move up-right ,then move right one step
            if(j < col-1){
                j++;
                result[k++] = matrix[i][j];
            }else if(i < row-1) {         //if we can't move right,just move down one step
                i++;
                result[k++] = matrix[i][j];
            }
            //After that,we will move down-left until it can't move
            while(i < row-1 && j >= 1) {
                i++;
                j--;
                result[k++] = matrix[i][j];
            }
            //if we can't move down-left,then move down
            if(i < row-1){
                i++;
                result[k++] = matrix[i][j];
            } else if(j < col-1) {        //if we can't move down,just move right
                j++;
                result[k++] = matrix[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        int mat[][] = { { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 } };

        System.out.println(Arrays.toString(findDiagonalOrder(mat)));
    }
}
