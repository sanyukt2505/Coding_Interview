package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/minimum-area-rectangle/
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points,
 * with sides parallel to the x and y axes.
 * If there isn't any rectangle, return 0.
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]       Output: 4
 */
public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        int res = Integer.MAX_VALUE;

        /** create a map of X and list of Y coordinates  */
        Map<Integer, List<Integer>> rows = new TreeMap<>();
        for (int [] point : points) {
            int x = point[0];
            int y = point[1];
            if (!rows.containsKey(x)) {
                rows.put(x, new ArrayList<>());
            }
            rows.get(x).add(y);
        }

        Map<String, Integer> lastX = new HashMap<>();

        for (int x: rows.keySet()) {
            List<Integer> row = rows.get(x);
            Collections.sort(row);

            /** for every x coordinate
             - sort the y cordinates list
             - and for each pair of (y2, y1), check if there is another x with same (y2,y1)
             - if so calculate the area
             */
            for (int i = 0; i < row.size(); i++) {
                for (int j = i+1; j < row.size(); j++) {
                    int y1 = row.get(i);
                    int y2 = row.get(j);
                    String key = y1 + "#" + y2;

                    if (lastX.containsKey(key)) {
                        res = Math.min(res, (x - lastX.get(key)) * (y2 - y1));
                    }
                    lastX.put(key, x);
                }
            }
        }
        return res < Integer.MAX_VALUE ? res : 0;
    }
}
