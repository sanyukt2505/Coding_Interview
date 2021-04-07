package com.leetcode.arrays.strings;

/**
 * Created by Vijay on 2/20/16.
 */
public class NeedleInHeystackBruteForce {
    public static void main(String[] args) {
        int needleFoundAt = strStr("AAAAAABB","AABBAAAAAAAAA");
        System.out.println("Needle found in Heystack at: " + needleFoundAt);
    }

    private static int strStr(String heystack, String needle) {
        for (int i = 0; ; i++) { // a pointer in the heystack
            for (int j = 0; ; j++) { // a pointer to needle length
                if (j == needle.length()) {
                    // Needle's length is reached, hence needle is found
                    // Return the pointer in the heystack where the needle is found
                    return i;
                }
                // Heystack length reached and needle is not found: return -1
                if (i + j == heystack.length()) return -1;

                // If the character in the needle as j is not equal to character in the heystack at i + j THEN break inner loop (counted by j)
                // Else if they match then you have found one more character in the needle, increment j and try to look if next character in the needle matches too
                if (needle.charAt(j) != heystack.charAt(i+j)) break;
            }
        }
    }
}
