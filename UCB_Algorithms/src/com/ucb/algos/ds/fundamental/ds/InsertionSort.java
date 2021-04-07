package com.ucb.algos.ds.fundamental.ds;

/**
 * Created by Vijay on 2/6/16.
 */
public class InsertionSort {
    public static void insertionSort(Comparable[] data) {
        for (int i = 1; i < data.length; i++) {
            Comparable current = data[i];
            int j = i;
            // While lower indexed (j-1) elements in the array are greater than current index being considered i
            // Keep assigning the value of lower index j-1 to higher index j UNTIL either j has reached 0 or we find a value lesser that current value
            while (j > 0 && data[j-1].compareTo(current) > 0) {
                data[j] = data[j-1];
                j--;
            }
            // We have found a place (index j) where the current element should be inserted
            data[j] = current;
        }
    }

    public static void main(String[] args) {
        Integer[] values = {12, 20, 10, 25, 35, 30};
        insertionSort(values);

        for (int val : values) {
            System.out.print(val+"\t");
        }
    }
}
