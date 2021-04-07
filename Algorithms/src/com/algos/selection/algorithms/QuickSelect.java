package com.algos.selection.algorithms;

import java.util.Random;

/**
 * Refer to: https://www.udemy.com/algorithmic-problems-in-java/learn/v4/t/lecture/4848412
 *
 * This is Hoare's algorithm to find k-th highest element.
 * It consists of 2 main operations: 1. partition 2. select
 *  1. Partition: we select a pivot value (a random value between indexFirst and indexLast).
 *          The elements are arranged such that elements to the left of pivot are less than pivot value and elements on the right are greater than pivot
 *  2. Select: Based on whether we are looking for k-th highest value or k-th lowest value, we can ignore the left or right side of pivot.
 * When you want k-th smallest: Just revert the condition in partition to check if nums[i] < nums[indexLast]
 *  As a result, elements on the left of the pivot would be smaller than the pivot which would be smaller than items on the right of it.
 *
 * Key to best case performance: Select a pivot such that as many items as possible are discarded every time. Discarding half of the array every time would be ideal.
 */
public class QuickSelect {
    private int[] nums;

    public QuickSelect(int[] nums) {
        this.nums = nums;
    }

    public int select(int k) {
        return select(0, nums.length - 1, k - 1);
    }

    private int select(int indexFirst, int indexLast, int k) {
        int pivot = partition(indexFirst, indexLast);

        if (pivot > k) {
            // pivot is greater than k: consider left subarray
            return select(indexFirst, pivot - 1, k);
        } else if (pivot < k) {
            // pivot is lesser than k: consider right subarray
            return select(pivot + 1, indexLast, k);
        }

        // pivot is equal to k: we have found the item we are looking for
        return nums[k];
    }

    private int partition(int indexFirst, int indexLast) {
        // Generate a random value that is pivot. This is significantly important step when considering the performance of algorithms.
        // If the pivot is not selected properly, the performance can degrade to O(N2)
        int pivot = new Random().nextInt(indexLast - indexFirst + 1) + indexFirst;

        // Swap the pivot with the indexLast. As a result, the pivot becomes indexLast i.e. last element
        swap(indexLast, pivot);

        // Iterate over array. Check for each element (at index i), if that is greater than element at indexLast(i.e. pivot). If so, swap element at index i with the indexFirst
        for (int i = indexFirst; i < indexLast; i++) {
            if (nums[i] > nums[indexLast]) { // change > to < if you are looking of k-th smallest element
                swap(i, indexFirst);
                indexFirst++;
            }
        }

        // Swap indexFirst and indexLast so that indexFirst contain the pivot
        swap(indexFirst, indexLast);

        // Return indexFirst: index of the pivot
        return indexFirst;
    }

    private void swap(int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, -5, 7, 10, 99};
        QuickSelect quickSelect = new QuickSelect(nums);

        System.out.println("1st largest: " + quickSelect.select(1));
        System.out.println();
        System.out.println("3rd largest: " + quickSelect.select(3));
    }
}
