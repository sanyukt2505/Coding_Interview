package com.leetcode.binary.search;


/**
 * Return the smallest character that is strictly larger than the search character,
 * otherwise return the first character in the string.
 * sortedStr : sorted list of letters, sorted in ascending order.
 * c : character for which we are searching.
 * Given the following inputs we expect the corresponding output:
 * ['c', 'f', 'j', 'p', 'v'], 'a' => 'c'
 * ['c', 'f', 'j', 'p', 'v'], 'c' => 'f'
 * ['c', 'f', 'j', 'p', 'v'], 'k' => 'p'
 * ['c', 'f', 'j', 'p', 'v'], 'z' => 'c' // The wrap around case
 * ['c', 'f', 'k'], 'f' => 'k'
 * ['c', 'f', 'k'], 'c' => 'f'
 * ['c', 'f', 'k'], 'd' => 'f'
 */
public class StrictlyLargerCharacter_LinkedIn {
    public static void main(String[] args) {
        char[] list = {'c', 'f', 'j', 'p', 'v'};
        char[] target = {'a', 'c', 'f', 'k', 'v', 'z'};
        for (char c : target) System.out.println(c + " -> " + findNextChar(list, c));
    }

    private static char findNextChar(char[] list, char c) {
        assert list.length > 0;
        int lo = 0, hi = list.length - 1;

        char result = list[0];

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (list[mid] == c) {
                if (mid < list.length - 1) return list[mid + 1];
                else return result;
            }
            else if (list[mid] < c) {
                lo = mid + 1;
            }
            else {
                result = list[mid];
                hi = mid - 1;
            }
        }

        return result;
    }
}
