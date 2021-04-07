package com.leetcode.arrays.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vijay on 5/2/16.
 */
public class PascalTriangle {

    public static List<List<Integer>> generate(int numRows) {
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

    public static int pascals(int x, int y) {
        if (y == 0 || x == y) {
            return 1;
        }
        return (pascals(x-1, y) + pascals(x-1, y-1));
    }

    public static void main(String[] args) {
        List<List<Integer>> rows = generate(6);

        for (List<Integer> row : rows) {
            System.out.println(row);
        }

        System.out.println("Pascal at 4, 1: " + pascals(4, 1));
        System.out.println("Pascal at 3, 2: " + pascals(3, 2));
    }
}
