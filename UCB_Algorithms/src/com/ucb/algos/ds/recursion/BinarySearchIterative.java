package com.ucb.algos.ds.recursion;

/**
 * Created by Vijay on 2/15/16.
 */
public class BinarySearchIterative {
    public static void main(String[] args) {
        int[] data = {10, 20, 30, 40, 50, 60};
        boolean containsTarget = binarySearchIterative(data, 30);
        System.out.println("Does data contain target? " + containsTarget);
    }

    public static boolean binarySearchIterative(int[] numbers, int key) {
        int low = 0, high = numbers.length-1;

        while(low <= high) {
            int mid = low + (high-low)/2;

            if (key < numbers[mid]) {
                high = mid - 1;
            } else if(key > numbers[mid]) {
                low = mid + 1;
            } else return true;
        }

        return false;
    }
}
