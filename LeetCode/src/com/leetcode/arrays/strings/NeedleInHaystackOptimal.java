package com.leetcode.arrays.strings;

public class NeedleInHaystackOptimal {
    public int strStr(String haystack, String needle) {
        int i, M = needle.length(), j, N = haystack.length();

        for (i = 0, j = 0; i < N && j < M; i++) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            } else {
                i = i - j;
                j = 0;
            }
        }

        if (j == M) return i - M;
        else return -1;
    }
}
