package problem.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Given a string s, find the length of the longest substring without repeating characters.
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 */
public class LongestSubstringWithoutRepeatingChar_3 {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length();
        Set<Character> occur = new HashSet<>();
        int low = 0, high = 0, start =0, end=0;
        while(high < len) {
            char c = s.charAt(high);
            if(!occur.contains(c)) {
                high++;
                occur.add(c);
                if (high-low > end-start) {
                    start = low;
                    end = high;
                }
            } else {
                occur.remove(s.charAt(low));
                low++;
            }
        }
        System.out.println(s.substring(start, end));
        return end-start;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("bacadzcfb"));
    }
}
