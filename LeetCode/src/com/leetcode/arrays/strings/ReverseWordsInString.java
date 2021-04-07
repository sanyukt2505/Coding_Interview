package com.leetcode.arrays.strings;

/**
 * Created by Vijay on 2/20/16.
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
        String reversedString = reverseWordsUsingBuffer("the sky is  blue");
        System.out.println("Reversed String: " + reversedString);
    }

    private static String reverseWordsUsingBuffer(String str) {
        StringBuilder reversed = new StringBuilder();
        int strLength = str.length();

        // Start traversing the string from length until it is greater than or equal to zero
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == ' ') {
                // Found a space in the sentence: Reduce the String to the part before the space assigning i to strLength
                strLength = i;
            } else if (i == 0 || str.charAt(i-1) == ' ') { // This loop is hit first. String's start has reached or Found a space at current index i - 1
                // Check if reversed is assigned some value already. It yes, then append space i.e. ' '
                if (reversed.length() != 0) {
                    reversed.append(' ');
                }
                // Append the substring after ' ' up to last index in the reduced String
                reversed.append(str.substring(i, strLength));
            }
        }

        return reversed.toString();
    }
}
