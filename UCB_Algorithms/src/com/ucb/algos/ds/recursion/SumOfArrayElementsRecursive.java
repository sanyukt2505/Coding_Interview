package com.ucb.algos.ds.recursion;

/**
 * Created by Vijay on 2/15/16.
 */
public class SumOfArrayElementsRecursive {
    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5};
        int sum = linearSum(data, data.length);
        System.out.println("Sum: " + sum);
    }

    private static int linearSum(int[] data, int n) {
        if (n == 0) {
            return 0;
        } else {
            return linearSum(data, n-1) + data[n-1];
        }
    }
}
