package com.leetcode.arrays.strings;

/**
 * Created by Vijay on 4/9/16.
 */
public class ValidNumber {
    public static void main(String[] args) {
        System.out.println("1 is valid? " + isValidNumber("1"));
        System.out.println("1 1 is valid? " + isValidNumber("1 1"));
        System.out.println("1. is valid? " + isValidNumber("1."));
        System.out.println("0.1 is valid? " + isValidNumber("0.1"));
        System.out.println("1.0 is valid? " + isValidNumber("1.0"));
        System.out.println("   1.0 is valid? " + isValidNumber("   1.0"));
        System.out.println("1.0    is valid? " + isValidNumber("1.0   "));
        System.out.println("1.0   1 is valid? " + isValidNumber("1.0   1"));
    }

    private static boolean isValidNumber(String str) {
        int i = 0, n = str.length();

        // Ignore white spaces
        while (i < n && Character.isWhitespace(str.charAt(i))) i++;
        boolean isNumeric = false;

        // Ignore the leading + and - characters
        if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) i++;

        // while String has digits keep incrementing i
        while (i < n && Character.isDigit(str.charAt(i))) {
            i++; isNumeric = true;
        }

        // if i is a decimal point, ignore it and handle further optional digits in hte string
        if (i < n && str.charAt(i) == '.') {
            i++;

            // process digits after decimal point
            while (i < n && Character.isDigit(str.charAt(i))) {
                i++; isNumeric = true;
            }
        }

        // Process whitespaces after decimal point
        while (i < n && Character.isWhitespace(str.charAt(i))) i++;

        // Notice: i == n
        // By this time i should be equal to n. If not there is an extra digit after space or some invalid character after trailing whitespaces
        return isNumeric && i == n;
    }
}
