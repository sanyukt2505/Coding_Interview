package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/number-of-good-ways-to-split-a-string/
 * https://www.youtube.com/watch?v=lRVpVUC5mQ4
 *
 * You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q
 * where its concatenation is equal to s and the number of distinct letters in p and q are the same.
 * Return the number of good splits you can make in s
 *
 * Input: s = "aacaba"      Output: 2    [("aac", "aba")  and ("aaca", "ba")]
 */
public class NumberOfGoodWaysSplit_1525 {
    public int numSplits(String s) {
        //creating 2 array to each track of character on left and right of split
        int[] left = new int[26];
        int[] right = new int[26];
        int goodWaysCount = 0;

        // initially split is at 0, so all goes to right
        for (int i = 0; i < s.length(); i++)
            right[s.charAt(i) - 'a']++;

        // for every index remove taht char from right and add to left
        for (int i = 0; i < s.length(); i++) {
            left[s.charAt(i) - 'a']++;
            right[s.charAt(i) - 'a']--;

            if (getDistinct(left) == getDistinct(right))
                goodWaysCount++;
        }
        return goodWaysCount;
    }

    // method to get number of distinct char in a array
    public int getDistinct(int[] arr) {
        int count = 0;
        for (int c: arr) {
            if (c != 0)
                count++;
        }
        return count;
    }
}
