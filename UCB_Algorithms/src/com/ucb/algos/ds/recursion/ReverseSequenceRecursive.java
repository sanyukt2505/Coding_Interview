package com.ucb.algos.ds.recursion;

import java.util.Arrays;

/**
 * Created by Vijay on 2/15/16.
 */
public class ReverseSequenceRecursive {
    public static void main(String[] args) {
        int[] data = {11, 22, 33, 44, 55};
        recursiveArray(data, 0, data.length-1);
        System.out.println(Arrays.toString(data));
    }

    private static int[] recursiveArray(int[] data, int lo, int hi) {
        if (lo > hi) {
            return data;
        } else {
            int temp = data[lo];
            data[lo] = data [hi];
            data[hi] = temp;
            return recursiveArray(data, lo + 1, hi - 1);
        }
    }
}
