package problem.leetcode.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break/
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Input: s = "leetcode", wordDict = ["leet", "code"]       Output: true
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]  Output: true
 *
 * Solution: https://www.youtube.com/watch?v=iWenZCZEBIA
 */
public class WordBreak_139 {
    /**
     * Keep a cache array DP that holds whether a string ending at an index i, is present in Dict or not
     * in this case dp will be = [T,F,F,F,T,F,F,F,T]
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int size = s.length();
        boolean[] dp = new boolean[size + 1];
        dp[0] = true;

        int maxLen = 0;
        for (String word: wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        for (int i = 0; i < size; i++) {
            for (int j = i; j <= size ; j++) {
                // improvement - if substring size if greater than longest work in dict, then skip
                if (j - i > maxLen)
                    continue;
                if (dp[i] && wordDict.contains(s.substring(i, j))) {
                    dp[j] = true;
                    break;
                }
            }
        }
        return dp[size];
    }

    /** brute force- recursive approach ---- O(n^n) */
    public boolean wordBreakRecursive(String s, List<String> wordDict) {
        return word_Break(s, new HashSet<>(wordDict), 0);
        // adding memoization
//        return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }

    public boolean word_Break(String s, Set<String> wordDict, int start) {
//        public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
//        if (memo[start] != null) { return memo[start]; }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end)) {
                return true;
            }
        }
        return false;
    }
}
