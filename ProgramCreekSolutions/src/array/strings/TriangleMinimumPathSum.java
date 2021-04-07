package array.strings;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vijay on 3/23/16.
 */
public class TriangleMinimumPathSum {
  public static void main(String[] args) {
    // List of ArrayLists
    ArrayList<ArrayList<Integer>> triangle = new ArrayList<>();

    Integer[] firstArr = {new Integer(2)};
    ArrayList<Integer> firstRow = new ArrayList<>(Arrays.asList(firstArr));
    triangle.add(firstRow);

    Integer[] secondArr = {new Integer(3), new Integer(4)};
    ArrayList<Integer> secondRow = new ArrayList<>(Arrays.asList(secondArr));
    triangle.add(secondRow);

    Integer[] thirdArr = {new Integer(6), new Integer(5), new Integer(7)};
    ArrayList<Integer> thirdRow = new ArrayList<>(Arrays.asList(thirdArr));
    triangle.add(thirdRow);

    Integer[] forthArr = {new Integer(4), new Integer(1), new Integer(8), new Integer(3)};
    ArrayList<Integer> fourthRow = new ArrayList<>(Arrays.asList(forthArr));
    triangle.add(fourthRow);

    System.out.println("Minimum Path Sum: "+ minimumTotal(triangle));
  }

  public static int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
    int[] total = new int[triangle.size()];
    int lastRowCountZeroBased = triangle.size() - 1;

    for (int i = 0; i < triangle.get(lastRowCountZeroBased).size(); i++) {
      total[i] = triangle.get(lastRowCountZeroBased).get(i);
    }

    // Iterate from second last row
    for (int i = triangle.size() - 2; i >= 0; i--) {
      for (int j = 0; j < triangle.get(i + 1).size() - 1; j++) {
        total[j] = triangle.get(i).get(j) + Math.min(total[j], total[j+1]);
      }
    }

    return total[0];
  }
}
