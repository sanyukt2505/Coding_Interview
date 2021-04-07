package com.leetcode.arrays.strings;

/**
 * LeetCode problem: https://leetcode.com/problems/compare-version-numbers/
 * Solution: https://discuss.leetcode.com/topic/6238/accepted-small-java-solution
 */
public class CompareVersionNumbers {
    public static int compareVersion(String version1, String version2) {
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);

        for (int i = 0; i < length; i++) {
            Integer v1 = (i < levels1.length) ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = (i < levels2.length) ? Integer.parseInt(levels2[i]) : 0;

            int compare = v1.compareTo(v2);

            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Compare 1.2 and 1.3: " + compareVersion("1.2", "1.3"));
        System.out.println("Compare 1.3 and 1.1: " + compareVersion("1.3", "1.1"));
    }
}
