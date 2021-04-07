package com.algos.strings.sorting;

import java.util.Arrays;

public class LongestRepeatedString {
    public static String lrs(String s) {
        int N = s.length();

        // Create an array for substrings and populate it by going from i = 0 to last index in String
        String[] suffixes = new String[N];
        for (int i = 0; i < N; i++) {
            suffixes[i] = s.substring(i, N);
        }

        // Sort suffixes array using Arrays utility method to sort (it is subtle better version of merge sort)
        Arrays.sort(suffixes);

        String lrs = "";

        for (int i = 0; i < N - 1; i++) {
            int len = lcp(suffixes[i], suffixes[i+1]);
            if (len > lrs.length()) {
                lrs = suffixes[i].substring(0, len);
            }
        }

        return lrs;
    }

    private static int lcp(String s, String t) {
        int N = Math.min(s.length(), t.length());

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) != t.charAt(i))
                return i;
        }

        return N;
    }

    public static void main(String[] args) {
        String s = "abcaatggaasngppqrsaatggaasngplmnop";
        String longestRepeatedString = lrs(s);

        System.out.println("Longest Repeated String is: " + longestRepeatedString);
    }
}
