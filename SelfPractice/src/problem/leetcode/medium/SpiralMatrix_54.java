package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/
 * Given a 2D matrix(square or rectangular) print it in spiral way.
 * e.g 1 2 3
 *     4 5 6
 *     7 8 9
 * Printing should be 1 2 3 6 9 8 7 4 5
 *
 * Solution:
 * Keep 4 pointers which are bounds for this matrix, up, down, left, right. Print each row or column and keep
 * incrementing and decrementing the bounds. As soon as up meets down or left meets right we are done.
 */
public class SpiralMatrix_54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int down = matrix.length - 1;

        while (left <= right && top <= down) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= down; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            /** if top <= down, tat means there is still a horizontal row to be processed */
            if (top <= down) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[down][i]);
                }
            }
            down--;

            /** if left <= right, tat means there is still a vertical column to be processed */
            if (left <= right) {
                for (int i = down; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
            }
            left++;
        }
        return result;
    }
}
