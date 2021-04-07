package problem.leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/decode-ways/solution/
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6)
 */
public class DecodeWays_91 {

    /**
     * We use an array for DP to store the results for subproblems.
     * A cell with index i of the dp array is used to store the number of decode ways for substring of s from index 0 to index i-1
     *
     * dp[i] = dp[i-1] + dp[i-2]
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;

        // dp is an array that store the num of ways to decode a string of length i
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= s.length(); i++) {
            // decoding can be done of oneDigit or 2 digits < 26
            int oneDigit = Integer.valueOf(s.substring(i-1, i));
            int twoDigit = Integer.valueOf(s.substring(i-2, i));

            if(oneDigit >= 1) {
                dp[i] += dp[i-1];
            }
            if(twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
    }

    /**
     * Return the actual decoded values
     */
    static Map<Integer, String> codes  = new HashMap<Integer, String>();

    private static void construct() {
        codes.put(1, "A");
        codes.put(2, "B");
        codes.put(3, "C");
        codes.put(12, "L");
        codes.put(23, "W");
    }

    public Set<String> decodeToString(String s) {

        Set<String> res = new HashSet<>();
        if (s == null || s.length() == 0)
            return res;

        construct();
        decode(s, "", res);
        return res;
    }

    public void decode(String s, String curr, Set<String> res) {
        if (s.length() == 0) {
            res.add(curr);
            return;
        }

        if (s.charAt(0) == '0')
            return;

        int oneDigit = Integer.valueOf(s.substring(0,1));
        decode(s.substring(1), curr + codes.get(oneDigit), res);

        if (s.length() >= 2) {
            int twoDigit = Integer.valueOf(s.substring(0, 2));
            if(twoDigit >= 10 && twoDigit <= 26) {
                decode(s.substring(2), curr + codes.get(twoDigit), res);
            }
        }
    }

    public static void main(String[] args) {
        DecodeWays_91 d = new DecodeWays_91();
        for (String str: d.decodeToString("12312")) {
            System.out.println(str);
        }
    }
}
