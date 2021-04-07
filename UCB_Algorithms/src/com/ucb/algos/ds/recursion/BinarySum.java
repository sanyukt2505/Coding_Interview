package com.ucb.algos.ds.recursion;

/**
 * Created by Vijay on 2/15/16.
 */
public class BinarySum {
    public static void main(String[] args) {
        int[] data = {11, 22, 33, 44, 55, 66, 77};
        int sum  = binarySum(data, 0, data.length - 1);
        System.out.println("Summation: " + sum);
    }

    private static int binarySum(int[] data, int lo, int hi) {
        if (lo > hi) {
            return 0;
        } else if (lo == hi) {
            return data[lo];
        } else {
            int mid = (lo + hi) / 2;
            return binarySum(data, lo, mid) + binarySum(data, mid + 1, hi);
        }
    }
}
