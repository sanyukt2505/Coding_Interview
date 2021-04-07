package com.leetcode.arrays.strings;

/**
 * This question expects us to NOT to use a buffer.
 * Solution link: https://discuss.leetcode.com/topic/8366/my-java-solution-with-explanation
 */
public class ReverseWordsInStringII {

    public static void reverseWords(char[] s) {
        // 1. Reverse the complete String (all characters in the array)
        reverse(s, 0, s.length-1);

        // 2. Reverse each word
        int start = 0;

        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }

        // 3. Reverse the last word, if there is only one word then this will solve the corner case
        reverse(s, start, s.length - 1);
    }

    public static void reverse(char[] s, int start, int end) {
        while (start < end) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        char[] s = "the sky is blue".toCharArray();
        reverseWords(s);

        String result = new String(s);
        System.out.println("Result: " + result);
    }
}
