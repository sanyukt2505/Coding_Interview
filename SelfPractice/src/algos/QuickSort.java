package algos;

import java.util.Arrays;

/**
 * // W = n^2, Avg = nlog(n), Best = nlog(n)
 * // Space = log(n)
 * // Quicksort is an elegant sorting algorithm that is very useful in most cases.
 * //
 * // It’s generally an “in-place” algorithm, with the average time complexity of O(n log n).
 * // Another interesting point to mention is that Java’s Arrays.sort() method uses Quicksort for sorting arrays of
 * // primitives.
 * // The implementation uses two pivots and performs much better than our simple solution
 */

public class QuickSort{

    public static void main(String []args){
        int[] a = new int[]{11,13,75,7,9,22,44,6,17,10};
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void quickSort(int[] a, int low, int high) {
        if (low > high)
            return;

        int index = partition(a, low, high);
        System.out.println("low " + low + " index "+ index +" high " + high);

        quickSort(a, low, index - 1);
        quickSort(a, index + 1, high);
    }

    //By the end of the partitioning, all elements less then the pivot are on the left of it
    // and all elements greater then the pivot are on the right of it.
    public static int partition(int[] a, int low, int high) {
        int pivot = a[(low + high) / 2];

        while (low < high) {
            while (a[low] < pivot)
                low++;

            while (a[high] > pivot)
                high--;

            if (low < high) {
                int temp = a[low];
                a[low] = a[high];
                a[high] = temp;
            }
        }
        return low;
    }
}