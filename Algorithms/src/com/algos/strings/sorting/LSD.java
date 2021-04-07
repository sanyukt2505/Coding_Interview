package com.algos.strings.sorting;

/**
 * Created by Vijay on 5/15/16.
 */
public class LSD {
    /**
     * Start from right most characters in String, key-index sort the Strings array then move the sort key to left (one character left of right most character), key-index sort AND
     * keep repeating the process until leftmost character is used as key-index sort key
     * @param a array of Strings
     * @param W array elements (Strings) are of fixed length W
     */
    public static void sort(String[] a, int W) {
        // We are considering extended ASCII character set (set of 256 characters)
        int R = 256;
        int N = a.length;
        String[] aux = new String[N];

        // key-index sort the string starting from right most character to left most character
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            for (int r = 0; r < R; r++) {
                count[r+1] += count[r];
            }
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {

    }
}
