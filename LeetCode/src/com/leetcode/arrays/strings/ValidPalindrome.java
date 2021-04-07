package com.leetcode.arrays.strings;

/**
 * Created by Vijay on 2/3/16.
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
    For example, "A man, a plan, a canal: Panama" is a palindrome. "race a car" is not a palindrome.
    Example Questions Candidate Might Ask:
    Q: What about an empty string? Is it a valid palindrome?
    A: For the purpose of this problem, we define empty string as valid palindrome.
 */
public class ValidPalindrome {
    private static boolean isValidPalindromeMyImpl(String str) {
        // String of length zero is considered as palindrome
        if (str.length() == 0) return true;

        // Ignore the case
        str = str.toLowerCase();

        for (int i = 0, j = str.length() - 1; i < str.length() / 2; i++, j--) {
            if (str.charAt(i) == str.charAt(j)) continue;
            else return false;
        }

        return true;
    }

    private static boolean isValidPalindromeSolution(String str) {
        int i = 0, j = str.length() - 1;

        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(str.charAt(i))) i++;
            while (i < j && !Character.isLetterOrDigit(str.charAt(j))) j--;

            if (Character.toLowerCase(str.charAt(i)) != Character.toLowerCase(str.charAt(j))) {
                return false;
            }
            i++; j--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Malayalam");

        System.out.println(isValidPalindromeMyImpl("Malayalam"));
        System.out.println(isValidPalindromeSolution("Malayalam"));

        System.out.println("race a car");

        System.out.println(isValidPalindromeMyImpl("race a car"));
        System.out.println(isValidPalindromeSolution("race a car"));

        System.out.println("A man, a plan, a canal: Panama");

        System.out.println(isValidPalindromeMyImpl("A man, a plan, a canal: Panama"));
        System.out.println(isValidPalindromeSolution("A man, a plan, a canal: Panama"));

        System.out.println(" A man, a plan, a canal: Panama");

        System.out.println(isValidPalindromeMyImpl(" A man, a plan, a canal: Panama"));
        System.out.println(isValidPalindromeSolution("  A man, a plan, a canal: Panama"));
    }
}
