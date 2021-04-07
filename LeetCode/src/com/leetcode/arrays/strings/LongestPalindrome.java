package com.leetcode.arrays.strings;

/**
 * O(N2) runtime, O(1) space
 * A palindrome can be expanded from its center and there are only 2n-1 such centers
 * Why 2n-1 such centers? Reason: The center of a palindrome can be in between two letters (e.g. for even number of letters)
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String longestPalindrome = longestPalindrome("abnitincd");
        System.out.println("Longest Palindrome is: " + longestPalindrome);
    }

    private static String longestPalindrome(String s) {
        int start = 0, end = 0;

        // i is the center. We move the center and try to expand around it
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            // Verify if there is a new palindrome lengthier than can already found
            if (len > end - start) {
                // Update start and end values for those pointers
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
