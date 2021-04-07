package com.algos.recursion;

public class BinarySearchRecursive {
    public static void main(String[] args) {
        int[] data = {10, 20, 30, 40, 50, 60};
        boolean containsTarget = binarySearchRecursive(data, 30, 0, data.length);
        System.out.println("Does data contain target? " + containsTarget);

        int targetFoundAt = binarySearch(data, 0, data.length, 30);
        System.out.println("Target found at: " + targetFoundAt);
    }

    /**
     * Return if the collection contains the target
     * @param data collection of elements as input
     * @param target value to be found
     * @param low value of lower index in the array
     * @param high value of higher index in the array
     * @return true/false depending on whether target is present in the array
     */
    private static boolean binarySearchRecursive(int[] data, int target, int low, int high) {
        if (low > high) {
            return false;
        }
        int mid = low + (high - low) / 2;
        if (target > data[mid]) {
            return binarySearchRecursive(data, target, mid + 1, high);
        } else if (target < data[mid]) {
            return binarySearchRecursive(data, target, low, mid - 1);
        } else
            return true;
    }

    /**
     * Return the index at which target is found in the collection
     * @param data collection of elements as input
     * @param startIndex value of lower index in the array
     * @param endIndex value of higher index in the array
     * @param item value to be found
     * @return index at which the element is found
     */
    private static int binarySearch(int[] data, int startIndex, int endIndex, int item) {
        if (endIndex < startIndex) {
            System.out.println("No solution found");
            return -1;
        }

        int middleIndex = startIndex + (endIndex - startIndex) / 2;

        if (item ==  data[middleIndex]) {
            return middleIndex;
        } else if (item < data[middleIndex]) {
            return binarySearch(data, startIndex, middleIndex - 1, item);
        } else {
            return binarySearch(data, middleIndex + 1, endIndex, item);
        }
    }
}
