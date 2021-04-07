package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/
 * A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so
 * that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 */
public class PartitionLabels_763 {

    public static List<Integer> partitionLabels(String s) {
        int[] lastOccr = new int[26];
        List<Integer> result = new ArrayList<>();

        /** create an arr that have the Max Last Occurence of a character */
        for(int i=0; i < s.length(); i++) {
            lastOccr[s.charAt(i) - 'a'] = i;
        }

        int currMaxIndex = 0;
        int start = 0;
        for(int i=0; i < s.length(); i++) {
            /**  for every char check the maxlastOccurence, if that is = i, add it to the list */
            currMaxIndex = Math.max(currMaxIndex, lastOccr[s.charAt(i) - 'a']);
            if (i == currMaxIndex) {
                result.add(currMaxIndex - start + 1);
                start = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }
}
