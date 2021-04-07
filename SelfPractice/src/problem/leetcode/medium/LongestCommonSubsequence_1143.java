package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 * A subsequence of a string is a new string generated from the original string with some characters(can be none)
 * deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not).
 * A common subsequence of two strings is a subsequence that is common to both strings.
 */
public class LongestCommonSubsequence_1143 {

    /**
     * 1) Consider the input strings “AGGTAB” and “GXTXAYB”. Last characters match for the strings.
     * So length of LCS can be written as: L(“AGGTAB”, “GXTXAYB”) = 1 + L(“AGGTA”, “GXTXAY”)
     * 2) Consider the input strings “ABCDGH” and “AEDFHR. Last characters do not match for the strings.
     * So length of LCS can be written as: L(“ABCDGH”, “AEDFHR”) = MAX ( L(“ABCDG”, “AEDFHR”), L(“ABCDGH”, “AEDFH”) )
     *
     * Time: O(2 ^ n)
     */
    public int lcs(char str1[],char str2[],int len1, int len2){
        
        if(len1 == str1.length || len2 == str2.length){
            return 0;
        }
        if(str1[len1] == str2[len2]){
            return 1 + lcs(str1,str2,len1+1,len2+1);
        }
        else{
            return Math.max(lcs(str1,str2,len1+1,len2),lcs(str1,str2,len1,len2+1));
        }
    }

    /**
     * In the above partial recursion tree, lcs(“AXY”, “AYZ”) is being solved twice.
     * If we draw the complete recursion tree, then we can see that there are many subproblems which are solved again and again.
     * So this problem has Overlapping Substructure property and recomputation of same subproblems can be avoided
     * by either using Memoization or Tabulation. Following is a tabulated implementation for the LCS problem.
     */
    public int lcsDynamic(char str1[],char str2[]){
    
        int temp[][] = new int[str1.length + 1][str2.length + 1];
        int max = 0;
        for(int i=1; i < temp.length; i++){
            for(int j=1; j < temp[i].length; j++){
                if(str1[i-1] == str2[j-1]) {
                    temp[i][j] = temp[i - 1][j - 1] + 1;
                }
                else
                {
                    temp[i][j] = Math.max(temp[i][j-1],temp[i-1][j]);
                }
                if(temp[i][j] > max){
                    max = temp[i][j];
                }
            }
        }
        return max;
    
    }
    
    public static void main(String args[]){
        LongestCommonSubsequence_1143 lcs = new LongestCommonSubsequence_1143();
        String str1 = "ABCDGHLQR";
        String str2 = "AEDPHR";

        System.out.println(lcs.lcs(str1.toCharArray(), str2.toCharArray(), 0, 0));
        int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
        System.out.print(result);
    }
    
    
    
}
