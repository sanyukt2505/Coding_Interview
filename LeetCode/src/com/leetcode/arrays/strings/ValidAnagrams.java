package com.leetcode.arrays.strings;

/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
    For example,
        s = "anagram", t = "nagaram", return true.
        s = "rat", t = "car", return false.
    Note:
        You may assume the string contains only lowercase alphabets.
 */
public class ValidAnagrams {

    public static boolean isAnagram(String s, String t) {
        int[] alphabet = new int[26];

        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            alphabet[t.charAt(i) - 'a']--;
        }

        for (int i : alphabet) {
            if (i != 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Are anagram and nagaram valid anagrams: " + isAnagram("anagram", "nagaram"));
        System.out.println();
        System.out.println("Are rat and car valid anagrams: " + isAnagram("rat", "car"));
    }
}
