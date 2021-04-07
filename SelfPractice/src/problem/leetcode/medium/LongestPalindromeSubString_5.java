package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromeSubString_5 {

    /**
     * Dynamic Programming approach
     * Consider the case "ababa". If we already knew that "bab" is a palindrome, it is obvious that "ababa" must be a palindrome
     * since the two left and right end letters are the same.
     * <p>
     * We define P(i,j) as following:
     * <p>
     * P(i,j) = true,    if the substring Si … Sj is a palindrome
     * ​        false,  otherwise.
     * ​Therefore P(i,j)=(P(i+1,j−1) and Si==Sj)
     *
     * if you have a palindrome, say "aba", then you know you have a bigger palindrome if you put the SAME character on both sides of it, say "cabac".
     * The "judge" variable is true if the two boundary characters are the same; otherwise, it's false. In other words, if Si != Sj,
     * then we cannot use the relation P(i,j) = P(i+1,j-1) && Si == Sj. But, when judge is true (i.e., Si == Sj), we
     * can save this new, larger palindrome in our DP table for future reference.
     * The if-block containing the "max" variable in its condition is the code that tracks which discovered palindrome is the largest so far.
     */
    public static String longestPalindromeDynamic(String s) {

        if (s == null || "".equals(s)) {
            return s;
        }

        int len = s.length();

        String ans = "";
        int max = 0;

        boolean[][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {

                boolean judge = s.charAt(j) == s.charAt(i);

                dp[j][i] = i - j > 2 ? dp[j + 1][i - 1] && judge : judge;

                if (dp[j][i] && i - j + 1 > max) {
                    max = i - j + 1;
                    ans = s.substring(j, i + 1);
                }
            }
        }
        return ans;
    }

    /**
     * Expand Around Center
     * In fact, we could solve it in O(n^2) time using only constant space.
     *
     * We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center,
     * and there are only 2n − 1 such centers.
     *
     * You might be asking why there are 2n − 1 but not nn centers? The reason is the center of a palindrome can be
     * in between two letters. Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        System.out.println(LongestPalindromeSubString_5.longestPalindromeDynamic("cdabbaefg"));
        System.out.println(LongestPalindromeSubString_5.longestPalindromeDynamic("banana"));
    }
}
