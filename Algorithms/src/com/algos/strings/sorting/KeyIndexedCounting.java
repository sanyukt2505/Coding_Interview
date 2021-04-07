package com.algos.strings.sorting;

/**
 * Sort an array a[] of N integers between 0 and R-1
 */
public class KeyIndexedCounting {
    public static void main(String[] args) {
        int[] numbers = {3, 0, 2, 5, 5, 1, 3, 1, 5, 1, 4, 0};

        sort(numbers, 6);

        for (int number : numbers) {
            System.out.println(number);
        }
    }

    private static void sort(int[] arr, int R) {
        int N = arr.length;
        int[] count = new int[R+1];
        int[] aux = new int[arr.length];

        /**
         * Compute frequencies of each int using key as index
         */
        for (int i = 0; i < N; i++) {
            count[arr[i]+1]++;
        }

        /**
         * Compute frequency cumulates which specify destinations
         */
        for (int r = 0; r < R; r++) {
            count[r+1] += count[r];
        }

        /**
         * Access cumulates using key as index to move items
         */
        for (int i = 0; i < N; i++) {
            aux[count[arr[i]]++] = arr[i];
        }

        /**
         * Copy back to original array
         */
        for (int i = 0; i < N; i++) {
            arr[i] = aux[i];
        }
    }
}
