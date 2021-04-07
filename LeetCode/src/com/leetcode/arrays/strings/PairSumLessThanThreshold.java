package com.leetcode.arrays.strings;

import java.util.Arrays;

/**
 * Created by Vijay on 5/2/16.
 */
public class PairSumLessThanThreshold {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int countOfPairs = getPairsCount(arr, 6);
        System.out.println("Count of pairs: " + countOfPairs);
    }

    static int getPairsCount(int[] array, int n) {
        Arrays.sort(array);

        int i = 0;
        int j = array.length - 1;
        int count = 0;

        while (i < j) {
            if (array[i] + array[j] <= n) {
                count += (j - i);
                i++;
            } else {
                j--;
            }
        }

        return count;
    }
}
