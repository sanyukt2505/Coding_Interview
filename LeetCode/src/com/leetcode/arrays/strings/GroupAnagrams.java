package com.leetcode.arrays.strings;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
    For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
    Return: [
                ["ate", "eat","tea"],
                ["nat","tan"],
                ["bat"]
            ]
    Note:
        1. For the return value, each inner list's elements must follow the lexicographic order.
        2. All inputs will be in lower-case.
 */
public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] ca = str.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<>());
            }
            map.get(keyStr).add(str);
        }

        // The following for loop sorts the anagrams in ascending order
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> groupAnagrams = groupAnagrams(strs);
        for (List groupAnagram : groupAnagrams) {
            System.out.println(groupAnagram);
        }
    }
}
