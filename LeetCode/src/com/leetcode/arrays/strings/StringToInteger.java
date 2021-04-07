package com.leetcode.arrays.strings;

/**
 * Discard as many whitespace characters as necessary until the first non-whitespace character is found
 * Starting from the first non-whitespace character, take an optional initial plus or minus sign followed by as many numerical digits as possible and interpret them as a numerical value
 * The String can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * If no valid conversion could be performed, a zero value is returned.
 * If the correct value is out of the range of representable values, the maximum integer value or the minimum integer value is returned.
 */
public class StringToInteger {
    // A reference value to verify the possible number overflow.
    // The Integer.MAX_VALUE is 2147483647 and so, the maxDiv10 value is 214748364.
    private static final int maxDiv10 = Integer.MAX_VALUE / 10;

    public static int atoi(String str) {
        int i = 0, strLength = str.length();

        // Discard as many whitespace characters as necessary until the first non-whitespace character is found
        while (i < strLength && Character.isWhitespace(str.charAt(i))) i++;

        // Consider the plus or minus characters before number.
        // Identify the sign multiplication factor based on character being + or -
        int sign = 1;
        if (i < strLength && str.charAt(i) == '+') {
            i++;
        } else if (i < strLength && str.charAt(i) == '-') {
            sign = -1;
            i++;
        }

        // Declare holding number which keeps on changing as long as next character in the string is a digit
        int num = 0;
        while (i < strLength && Character.isDigit(str.charAt(i))) {
            // Get digit to be appended in the end of the number
            int digit = Character.getNumericValue(str.charAt(i));

            // Verify the number overflow case
            // Remember the digit >= 8 case
            if (num > maxDiv10 || num == maxDiv10 && digit >= 8) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // There is no possible number overflow. Multiply the num value by 10 and add digit to it.
            num = num * 10 + digit;

            // Increment i so that next character in the String will be processed.
            i++;
        }
        return sign * num;
    }

    public static void main(String[] args) {
        System.out.println(atoi("    -963"));
    }
}
