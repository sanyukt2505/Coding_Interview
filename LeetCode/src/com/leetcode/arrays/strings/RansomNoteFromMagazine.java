package com.leetcode.arrays.strings;

/**
 * LeetCode problem: https://leetcode.com/problems/ransom-note/
 * O(n) solution: https://discuss.leetcode.com/topic/53864/java-o-n-solution-easy-to-understand
 */
public class RansomNoteFromMagazine {

    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];

        // Make a pass through complete magazine setting incrementing the count of character - 'a' value each time
        for (int i = 0; i < magazine.length(); i++) {
            arr[magazine.charAt(i) - 'a']++;
        }

        // Now make a pass through the ransom note, if reducing count of character - 'a' results into negative value, then magazine didn't have that character
        for (int i = 0; i < ransomNote.length(); i++) {
            if (--arr[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("a", "b"));
        System.out.println(canConstruct("aa", "ab"));
        System.out.println(canConstruct("aa", "aab"));
    }
}
