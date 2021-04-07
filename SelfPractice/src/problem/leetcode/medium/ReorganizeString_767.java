package problem.leetcode.medium;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/reorganize-string/
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * If possible, output any possible result.  If not possible, return the empty string.
 * Input: S = "aab"      Output: "aba"
 * Input: S = "aaab"     Output: ""
 *
 * Solution:
 *  - Find two most repeated characters and place them side by side
 *  - Decrement and Update the count of above 2 characters
 *  - Repeat the above steps for next 2 characters.
 */
public class ReorganizeString_767 {
    public String reorganizeString(String S) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: S.toCharArray()) {
            map.put(c, map.getOrDefault(c,0) + 1);
        }

        StringBuilder res = new StringBuilder();
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        maxHeap.addAll(map.keySet());

        while (maxHeap.size() > 1) {
            // get 2 most repeating characters
            char curr = maxHeap.poll();
            char next = maxHeap.poll();
            res.append(curr);
            res.append(next);
            map.put(curr, map.get(curr) - 1);
            map.put(next, map.get(next) - 1);
            if (map.get(curr) > 0) {
                maxHeap.add(curr);
            }
            if (map.get(next) > 0) {
                maxHeap.add(next);
            }
        }
        //if heap is not empty - then that is the last character
        // but if its counts is > 1, we can't find another next character to put after it, to create a non repeating
        if (!maxHeap.isEmpty()) {
            char last = maxHeap.poll();
            if (map.get(last) > 1) {
                return "";
            }
            res.append(last);
        }
        return res.toString();
    }
}
