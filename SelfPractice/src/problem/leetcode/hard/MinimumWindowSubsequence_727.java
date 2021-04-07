package problem.leetcode.hard;

/** https://www.youtube.com/watch?v=W2DvQcDPD9A&feature=emb_err_woyt
 * https://leetcode.com/problems/minimum-window-subsequence/
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such minimum-length windows, return the one with the left-most starting index.
 * Input:      S = "abccdebdde", T = "bde"       Output: "bcde"
 */
public class MinimumWindowSubsequence_727 {
    public static String minWindow(String S, String T) {
        String ans = "" ;
        int j=0;

        /** Two scan approach
         *  First move left to right and record the matching position
         *  Now use those position to move from right to left
         *  */
        for(int i=0; i<S.length(); i++) {
            if(S.charAt(i) == T.charAt(j)){
                j++;
                /**  when second string is full matched and J is out of bound starting moving from right to left
                 * This will take care of case "bcbde"
                 * -- left to right will match with "bcbde", but right to left will match to "bde"*/
                if(j >= T.length()){
                    int end = i+1;
                    j--;
                    while(j >= 0){
                        if(S.charAt(i)==T.charAt(j))
                            j--;
                        i--;
                    }
                    i++;
                    String temp = S.substring(i, end);
                    if(ans.length()==0|| temp.length()<ans.length() )
                        ans = temp;
                    j = 0;
                }

            }
        }
        return ans;
    }

    public static void main(String[] args) {
       System.out.println(minWindow("abccdebdde", "bde"));
    }
}
