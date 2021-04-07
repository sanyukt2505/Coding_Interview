package com.leetcode.arrays.strings;

/**
 * Problem: https://leetcode.com/problems/plus-one/
 * Solution: https://discuss.leetcode.com/topic/24288/my-simple-java-solution
 */
public class PlusOne {
    public static int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n-1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] newNumber = new int[n+1];
        newNumber[0] = 1;

        return newNumber;
    }

    public static void main(String[] args) {
        int[] digits = {1, 2, 9};
        int[] result = plusOne(digits);

        for (int i : result) {
            System.out.print(i);
        }
    }
}
