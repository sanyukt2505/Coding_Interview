package com.leetcode.arrays.strings;

/**
 * Given a String, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", length of which is 3.
 * For the string "bbbbbb", the longest substring is "b", with the length of 1.
 */
public class LongestSubstringWithoutRepCharacters {

  public static void main(String[] args) {
    int lengthOfLongestSubstring = lengthOfLongestSubstring("ababc");
    System.out.println("Length of Longest Substring in ababc: " + lengthOfLongestSubstring);

    System.out.println("");

    lengthOfLongestSubstring = lengthOfLongestSubstring("bbbbb");
    System.out.println("Length of Longest Substring in bbbbb: " + lengthOfLongestSubstring);
  }

  private static int lengthOfLongestSubstring(String str) {
    // Create an array of boolean type. The indexes represent ascii values of characters
    // Use this simple table to store the characters that have appeared
    boolean[] exist = new boolean[256];

    int i = 0, maxLen = 0;

    // Iterate over length of String. e.g. In case of "ababc" length is 5
    for (int j = 0; j < str.length(); j++) {

      // exists[str.charAt(j)] is true means this is a repeated character
      while (exist[str.charAt(j)]) {
        /**
         * When you have found a repeated character, it means that the current substring is a potential maximum, so update the maximum if necessary.
         * It also means that the repeated character must have appeared before at an index i where i is less than j.
         *
         * Since you know that all substrings that start before or at index i would be less than your current maximum,
         * you can safely start to look for the next substring with head which starts exactly at i+1
         */
        exist[str.charAt(i)] = false;
        // increment i as mentioned in the description above
        i++;
      }

      exist[str.charAt(j)] = true;
      maxLen = Math.max(j - i + 1, maxLen);
    }

    return maxLen;
  }
}
