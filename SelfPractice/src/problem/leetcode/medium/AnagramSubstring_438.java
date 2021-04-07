package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Input:  s: "cbaebabacd" p: "abc"     Output: [0, 6]
 *
 * Variation
 * Given two strings a and b, find whether any anagram of string a is a sub-string of string b.
 * For eg: if a = xyz and b = afdgzyxksldfm then the program should return true.
 */
public class AnagramSubstring_438 {
    /**
     * Sliding window approach
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] charCount = new int[26];

        if (s == null || s.length() == 0)
            return res;

        // create an array of count of characters
        for (char c: p.toCharArray()) {
            charCount[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        int count = p.length();
        // sliding window
        // characters that are currently in sliding window, we keep a count of only those
        while (right < s.length()) {
            if(charCount[s.charAt(right++) - 'a']-- >= 1) {
                count--;
            }
            if (count == 0) {
                res.add(left);
            }
            if (right - left == p.length() && charCount[s.charAt(left++) - 'a']++ >= 0) {
                count++;
            }
        }
        return res;
    }

    /** --------- Cloning the array and doing the count  -------- */
    static boolean substringAnagram(String a, String b){
        List<Integer> startIndex = new ArrayList<>();

        int[]table = new int[256];
        Arrays.fill(table, 0);
        int[]orig_table=new int[256];
        Arrays.fill(orig_table, 0);

        for(int i=0;i<a.length();i++){
            table[a.charAt(i)]++;
            orig_table[a.charAt(i)]++;
        }

        int count=0;
        for(int i=0;i<b.length();i++){
            if(table[b.charAt(i)]!=0){
                table[b.charAt(i)]--;
                count++;
                if(count == a.length()){  //match found
                    startIndex.add(i - count);
                    return true;
                }
            }
            else if(count > 0){       //reset
                count=0;
                table=orig_table.clone();
            }
            //else do nothing
        }
        return false;
    }

    public static void main(String args[]){
        String a="xxyz";
        String b="afyzxydgxzyxksldfm";
        System.out.println(substringAnagram(a,b));

        for(Integer index: findAnagrams("cbaebabacd", "abc"))
            System.out.println(index);
    }
}
