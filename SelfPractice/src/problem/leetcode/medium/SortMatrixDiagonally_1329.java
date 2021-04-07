package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.
 * Input: mat = [   [3,3,1,1],
 *                  [2,2,1,2],
 *                  [1,1,1,2]]
 * Output: [    [1,1,1,1],
 *              [1,2,2,2],
 *              [1,2,3,3]]
 */
public class SortMatrixDiagonally_1329 {

    public int[][] diagonalSort(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        // create the Map of list of each row
        // for key use rowNum - colNum
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                int diff = i - j;

                if(!map.containsKey(diff)) {
                    map.put(diff, new ArrayList<>());
                }
                map.get(diff).add(mat[i][j]);
            }
        }

        // sort each list
        for (List list: map.values()) {
            Collections.sort(list);
        }

        // add the sorted values back to matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int diff = i - j;
                mat[i][j] = map.get(diff).get(0);
                map.get(diff).remove(0);
            }
        }
        return mat;
    }
}
