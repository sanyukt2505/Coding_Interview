package com.leetcode.math;

/**
 * Given two binary strings, return their sum (also a binary string).
   For example,
     a = "11"
     b = "1"
     Return "100".
 */
public class BinarySum {
    public static void main(String[] args) {
        String a = "11", b = "1";
        String binarySum = addBinary(a, b);
        System.out.println("Binary sum of " + a + " and " + b + " is: " + binarySum);
    }

    private static String addBinary(String a, String b) {
        if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;

        StringBuilder builder = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, c = 0;

        while (i >= 0 || j >= 0 || c == 1) {
            c += (i >= 0) ? a.charAt(i--) - '0' : 0;
            c += (j >= 0) ? b.charAt(j--) - '0' : 0;

            builder.append((char) ('0' + c % 2));
            c = c > 1 ? 1 : 0;
        }

        return builder.reverse().toString();
    }
}
