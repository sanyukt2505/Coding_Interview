package com.leetcode.arrays.strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

    For example, given
        s = "leetcode",
        dict = ["leet", "code"].

    Return true because "leetcode" can be segmented as "leet code".
 */
public class WordBreak {
    public static void main(String[] args) {
        Set<String> dict = new HashSet<>();
        dict.add("leet");
        dict.add("code");
        dict.add("human");

        boolean canBeSegmented = wordBreak("leetcode", dict);
        System.out.println("Can leetcode be segmented?: " + canBeSegmented);

        canBeSegmented = wordBreak("leetcodee", dict);
        System.out.println("Can leetcodee be segmented?: " + canBeSegmented);

        List<String> wordsList = wordBrokenInto("leetcode", dict);
        for (String word : wordsList) {
            System.out.print(word + '\t');
        }
    }

    public static boolean wordBreak(String s, Set<String> dict) {
        boolean[] f = new boolean[s.length() + 1];

        f[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && dict.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }

        return f[s.length()];
    }

    public static List<String> wordBrokenInto(String s, Set<String> dict) {
        boolean[] f = new boolean[s.length() + 1];
        List<String> wordList = new ArrayList<>();

        f[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && dict.contains(s.substring(j, i))) {
                    f[i] = true;
                    wordList.add(s.substring(j, i));
                }
            }
        }

        return wordList;
    }
}
