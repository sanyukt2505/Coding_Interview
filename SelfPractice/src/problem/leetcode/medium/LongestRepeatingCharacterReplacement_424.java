package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 * https://www.youtube.com/watch?v=00FmUN1pkGE&t
 *
 * Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
 * In one operation, you can choose any character of the string and change it to any other uppercase English character.
 * Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
 * Input:   s = "AABABBA", k = 1
 * Output:   4
 */
public class LongestRepeatingCharacterReplacement_424 {

    public static int characterReplacement(String s, int k) {

        if (s == null) return 0;
        int n = s.length();

        int[] charsCounter = new int[26];
        int start = 0;
        int repeatCount = 0;
        int maxLength = 0;

        for (int end = 0; end < n; end++) {

            char ch = s.charAt(end);
            charsCounter[ch - 'A']++;

            // count of the current character
            repeatCount = Math.max(repeatCount, charsCounter[ch - 'A']);

            // if my current window (end-start) minus the current repeating char < k, then we are good
            // else we need to reduce the window size
            while (end - start + 1 - repeatCount > k) {
                // as u r reducing the window, decrease count of charAt(start)
                charsCounter[s.charAt(start) - 'A']--;
                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABCBABBA", 2));
    }
}
