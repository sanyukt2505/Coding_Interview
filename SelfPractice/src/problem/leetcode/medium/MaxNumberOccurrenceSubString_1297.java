package problem.leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/
 *
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 *
 *Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 ocurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize)
 */
public class MaxNumberOccurrenceSubString_1297 {
    /**
     * Create all the subString of size minSize
     * The subStrings that matches the maxLetters criteria, update the count in a Map
     */
    public static int maxFreq2(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        HashMap<String,Integer> freqMap = new HashMap<>();
        int maxfreq = -1;
        for(int i = 0; i < n - minSize + 1; i++){
            String substr = s.substring(i,i + minSize);
            Set<Character> charSet = new HashSet<>();
            for(char c : substr.toCharArray()){
                charSet.add(c);
            }
            if(charSet.size() <= maxLetters){
                freqMap.put(substr,freqMap.getOrDefault(substr,0) + 1);
                maxfreq = Math.max(maxfreq,freqMap.get(substr));
            }
        }
        return maxfreq == -1 ? 0 : maxfreq;
    }

    /**
     * create sliding windows of size that matches the minSize & maxSize
     * Also keep track of unique character count and update them when ever the size of the window changes
     */
    public static int maxFreqSlidingWindow(String s, int maxLetters, int minSize, int maxSize) {
        int[] charCount = new int[26];      /** to keep count of each letters */
        int start = 0;
        int letterCount = 0;        /** count the number of unique letters */
        int maxCount = 0;

        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (charCount[ch - 'a'] == 0)
                letterCount++;

            charCount[ch - 'a']++;

            if (letterCount > maxLetters) {
                charCount[start]--;
                if (charCount[start] == 0)
                    letterCount--;

                start++;
            }

            if (end - start + 1 >= minSize && end - start + 1 < maxSize) {
                int occrCount = getOccurenceCount(s, s.substring(start, end + 1));

                if (maxCount < occrCount)
                    maxCount = occrCount;
            }
        }
        return maxCount;
    }

    public static int getOccurenceCount(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {
            lastIndex = str.indexOf(findStr,lastIndex);

            if(lastIndex == -1)
                break;

            count++;
            lastIndex += findStr.length();
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(maxFreqSlidingWindow("aababcaab", 2, 3, 4));
        System.out.println(maxFreq2("aababcaab", 2, 3, 4));
    }
}
