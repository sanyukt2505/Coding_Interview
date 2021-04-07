package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle-ii/
 * Given an integer rowIndex, return the rowIndexth row of the Pascal's triangle.
 */
public class PascalsTriangle_118_119 {
    /** Using two list
     * at level i, create a list of i+1 and add 1 at 0 and i
     * use prev list to calculate the reat number in middle 0 - i
     */
    public List<Integer> getRowWithoutRecursion_119(int rowIndex) {
        List<Integer> curr = new ArrayList<>();
        List<Integer> prev = new ArrayList<>();
        curr.add(1);
        prev.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            curr = new ArrayList<>(i+1);
            curr.add(1);

            for (int j = 1; j < i; j++) {
                curr.add(prev.get(j - 1) + prev.get(j));
            }
            curr.add(1);

            prev = curr;
        }
        return prev;
    }
    /**  generates Row recursive
     * For rowIndex 6 : [1, 5, 10, 10, 5, 1]
     */
    public static List<Integer> generateRowRecursive(int rowIndex) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            ans.add(pascals(rowIndex, i));
        }
        return ans;
    }

    /**  generate Number at an IndexPascal at 4, 1: 4
     */
    public static int pascals(int x, int y) {
        if (y == 0 || x == y) {
            return 1;
        }
        return (pascals(x-1, y) + pascals(x-1, y-1));
    }

    public static List<List<Integer>> generateTriangle_118(int numRows) {
        List<List<Integer>> allrows = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j+1));
            }
            allrows.add(new ArrayList<>(row));
        }

        return allrows;
    }

    public static void main(String[] args) {
        List<List<Integer>> rows = generateTriangle_118(6);
        for (List<Integer> row : rows) {
            System.out.println(row);
        }

        System.out.println(generateRowRecursive(4));
        System.out.println("Pascal at 4, 1: " + pascals(4, 1));
        System.out.println("Pascal at 3, 2: " + pascals(3, 2));
    }
}
