package problem.leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/string-transforms-into-another-string/
 * two strings s1 and s2 of same length, determine whether you can transform s1 into s2 by doing 0 or more conversions.
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 * Return true if and only if you can transform str1 into str2.
 *
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 * Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
 */
public class StringTranformToAnother_1153 {
    public boolean canConvert(String str1, String str2) {
        if(str1.equals(str2))
            return true;

        /** create a map of which element in s1 maps to what eleent in s2  */
        HashMap<Character, Character> mp = new HashMap<>();
        for(int i = 0; i < str1.length(); i++)
        {
            /** if an entry exists in map and current char comparision mismatch doesn't match map entry */
            if(mp.containsKey(str1.charAt(i)) && mp.get(str1.charAt(i)) != str2.charAt(i))
                return false;
            mp.put(str1.charAt(i),str2.charAt(i));
        }
        return new HashSet<Character>(mp.values()).size() < 26;

    }
}
