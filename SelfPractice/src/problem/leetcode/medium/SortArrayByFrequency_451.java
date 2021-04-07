package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/sort-characters-by-frequency/
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * Input:   "tree"
 * Output:  "eert"
 * This can also be applied to an array
 */
public class SortArrayByFrequency_451 {
    public String frequencySort(String s) {
        HashMap<Character, Integer> countMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        /** Count up the occurances.   */
        for (char c: s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        /** Make a list of the keys, sorted by frequency.    */
        List<Character> charList = new ArrayList<>(countMap.keySet());
        Collections.sort(charList, (a, b) -> countMap.get(b) - countMap.get(a));

        /** Convert the counts into a string with a sb.   */
        for (Character c: charList) {
            for (int i = 0; i < countMap.get(c); i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
