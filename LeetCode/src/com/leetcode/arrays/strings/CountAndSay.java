package com.leetcode.arrays.strings;

/**
 The count-and-say sequence is the sequence of integers beginning as follows:
    1, 11, 21, 1211, 111221, ...

    1 is read off as "one 1" or 11.
    11 is read off as "two 1s" or 21.
    21 is read off as "one 2, then one 1" or 1211.

    Given an integer n, generate the nth sequence.

    Note: The sequence of integers will be represented as a string.
 */

public class CountAndSay {
    public static void main(String[] args) {
        int n = 4;
        String countAndSayResult = countAndSay(n);
        System.out.println("Count and Say result: " + countAndSayResult);
    }

    private static String countAndSay(int n) {
        String result = "1";

        for (int outer = 1; outer < n; outer++) {
            String previous = result;
            result = "";

            int count = 1;
            char say = previous.charAt(0);

            for (int i = 1; i < previous.length(); i++) {
                if (previous.charAt(i) != say) {
                    result = result + count + say;
                    count = 1;
                    say = previous.charAt(i);
                } else count++;
            }

            result = result + count + say;
        }

        return result;
    }
}
