package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 * Input: "bbbab"       Output: 4
 *
 * Time complexity - O(n2)
 * Space complexity - O(n2
 *
 * Youtube link - https://youtu.be/_nCsPn7_OgI
 * http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence_516 {

    public int calculate1(char []str){
        int T[][] = new int[str.length][str.length];
        // Build the table. Note that the lower diagonal values of table are useless and not filled in the process.
        for(int i=0; i < str.length; i++){
            T[i][i] = 1;
        }

        for(int l = 2; l <= str.length; l++){
            for(int i = 0; i < str.length-l + 1; i++){
                int j = i + l - 1;
                if(l == 2 && str[i] == str[j]){
                    T[i][j] = 2;
                }else if(str[i] == str[j]){
                    T[i][j] = T[i + 1][j-1] + 2;
                }else{
                    T[i][j] = Math.max(T[i + 1][j], T[i][j - 1]);
                }
            }
        }
        return T[0][str.length-1];
    }

    /**
     * 1. Every single character is a palindrome of length 1
     *      L(i, i) = 1 for all indexes i in given sequence
     * 2. IF first and last characters are not same
     *      If (X[i] != X[j]), then  L(i, j) =  max{L(i + 1, j),L(i, j - 1)}
     * 3. If there are only 2 characters and both are same
     *      Else if (j == i + 1), then L(i, j) = 2
     * 4. If there are more than two characters, and first and last characters are same
     *      Else L(i, j) =  L(i + 1, j - 1) + 2
     */
    public int calculateRecursive(char str[], int start, int len){
        if(len == 1){
            return 1;
        }
        if(len == 0){
            return 0;
        }
        if(str[start] == str[start+len-1]){
            return 2 + calculateRecursive(str,start+1,len-2);
        }else{
            return Math.max(calculateRecursive(str, start+1, len-1), calculateRecursive(str, start, len-1));
        }
    }
    
    public static void main(String args[]){
        LongestPalindromicSubsequence_516 lps = new LongestPalindromicSubsequence_516();
        String str = "agbdba";
        int r1 = lps.calculateRecursive(str.toCharArray(), 0, str.length());
        int r2 = lps.calculate1(str.toCharArray());
        System.out.print(r1 + " " + r2);
    }
    
}
