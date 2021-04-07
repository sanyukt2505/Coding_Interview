package com.leetcode.arrays.strings;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"ABCD", "ABCDE", "ABCED", "ABDC", "ABCDEF"};
        String longestCommonPrefix = longestCommonPrefix(strs);
        System.out.println("Longest Common Prefix is: " + longestCommonPrefix);
    }

    private static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(pre) != 0) {
                pre = pre.substring(0, pre.length()-1);
            }
        }
        return pre;
    }
}
