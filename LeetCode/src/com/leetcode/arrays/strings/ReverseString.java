package com.leetcode.arrays.strings;

/**
 * Write a function that takes a string as input and returns the string reversed.
    Example:
    Given s = "hello", return "olleh".
 */
public class ReverseString {
    public static String reverseString(String s) {
        if (s == null || s.length() == 0) return s;

        StringBuilder sb = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--){
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        if(reverseString("hello").equals("olleh")){
            System.out.println("String Reversal is working");
        }
    }
}
