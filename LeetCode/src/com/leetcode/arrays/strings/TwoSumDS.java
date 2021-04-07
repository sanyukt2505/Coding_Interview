package com.leetcode.arrays.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
     add(input) – Add the number input to an internal data structure.
     find(value) – Find if there exists any pair of numbers which sum is equal to the value.
   For example, add(1); add(3); add(5); find(4) returns true; find(7) returns false
 */
public class TwoSumDS {
    private Map<Integer, Integer> table = new HashMap<>();

    public void add(int input) {
        int count = table.containsKey(input) ? table.get(input) : 0;
        table.put(input, count+1);
    }

    public boolean find(int val) {
        for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
            int num = entry.getKey();
            int y = val - num;

            if (y == num) {
                if (entry.getValue() >= 2) return true;
            } else if (table.containsKey(y)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TwoSumDS twoSumDS = new TwoSumDS();
        twoSumDS.add(1);
        twoSumDS.add(3);
        twoSumDS.add(5);

        twoSumDS.find(4);
        twoSumDS.find(7);
    }
}
