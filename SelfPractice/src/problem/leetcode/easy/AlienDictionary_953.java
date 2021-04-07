package problem.leetcode.easy;

/**
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet,
 * return true if and only if the given words are sorted lexicographicaly in this alien language.
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 */
public class AlienDictionary_953 {
    public static int[] charMap;

    public static boolean isAlienSorted(String[] words, String order) {
        charMap = new int[26];

        for (int i=0; i < order.length(); i++) {
            charMap[order.charAt(i) - 'a'] = i;
        }

        for (int i=0; i < words.length - 1; i++) {
            if(compare(words[i], words[i+1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static int compare(String word1, String word2) {
        int i=0, j=0;
        int charCompare = 0;

        while (i<word1.length() && j<word2.length() && charCompare ==0) {
            charCompare = charMap[word1.charAt(i) - 'a'] - charMap[word2.charAt(j) - 'a'];
            i++;
            j++;
        }

        if(charCompare == 0) {
            return word1.length() - word2.length();
        } else {
            return charCompare;
        }
    }

    public static void main(String[] args) {
        System.out.println(isAlienSorted(new String[] {"hello", "heetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
    }
}
