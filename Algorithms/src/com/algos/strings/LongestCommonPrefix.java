package com.algos.strings;

/**
 * Created by Vijay on 2/4/16.
 */
public class LongestCommonPrefix {
    private static String lcpString(String str1, String str2) {
        int N = Math.min(str1.length(), str2.length());

        for (int i = 0; i < N; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                 return str1.substring(0, i);
            }
        }

        return str1.substring(0, N);
    }

    public static void main(String[] args) {
        System.out.println(lcpString("prefetch", "prefix"));
    }
}
