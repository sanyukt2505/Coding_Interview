package com.leetcode.math;

/**
 * Created by Vijay on 2/20/16.
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        System.out.println("Is 12334321 Palindrome number: " + isPalindromeSolution(12334321));
    }

    private static boolean isPalindromeSolution(int x) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) {
            div = div * 10;
        }

        while (x != 0){
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;

            // Chop the left most number: x % div
            // Chop the right most number: x / 10
            x = (x % div) / 10;

            // Since two digits are chopped (on from left and one from right), change the div value to div/100
            div /= 100;
        }

        return true;
    }
}
