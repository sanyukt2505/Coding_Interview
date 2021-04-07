package com.leetcode.arrays.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
    For example:    "code" -> False, "aab" -> True, "carerac" -> True.
 */
public class PalindromePermutation {

    /**
     * Iterate over String s, adding current character to set if the set doesn't contain that character,
     *                        OR removing current character from set if the set contains that character
     * When the iteration is finished, just return set.size() == 0 || set.size() == 1
     * set.size() == 0 : A situation when there are even number of any character in the String
     * set.size() == 1 : A situation when there are even number of any character EXCEPT one character in the String
     * @param s
     * @return
     */
    public static boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
            } else {
                set.remove(s.charAt(i));
            }
        }
        return set.size() == 0 || set.size() == 1;
    }

    public static void main(String[] args) {
        System.out.println("Can permute palindrome 'aab'?: " + canPermutePalindrome("aab"));
        System.out.println("Can permute palindrome 'code'?: " + canPermutePalindrome("code"));
        System.out.println("Can permute palindrome 'carerac'?: " + canPermutePalindrome("carerac"));
    }
}
