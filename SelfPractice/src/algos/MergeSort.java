package algos;

import java.util.Arrays;

/**
 * W = nlog(n), Avg = nlog(n), Best = nlog(n)
 *  Space = O(n)
 * Divide: In this step, we divide the input array into 2 halves, the pivot being the midpoint of the array.
 *         This step is carried out recursively for all the half arrays until there are no more half arrays to divide.
 * Conquer: In this step, we sort and merge the divided arrays from bottom to top and get the sorted array.
 *
 * Useful for:
 *      - Large size list
 *      - Merge linked list
 *      - External Sorting of files
 */

public class MergeSort{

    public static void main(String []args){
        int[] a = new int[]{11,13,75,7,9,22,44,6,17,10};
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void mergeSort(int[] a, int low, int high) {

        if (low >= high)
            return;
        int mid = low + (high - low) / 2;
//        System.out.println("low " + low + " mid "+ mid +" high " + high);
        mergeSort(a, low, mid);
        mergeSort(a, mid + 1, high);
        merge(a, low, mid + 1, high);
    }

    public static void merge(int[] a, int low, int mid, int high) {

        int n = high - low + 1;
        int middle = mid - 1;
        int lower = low;
        int j = 0;
        int[] work = new int[n];

        while (low <= middle  && mid <= high) {
            if (a[low] < a[mid])
                work[j++] = a[low++];
            else
                work[j++] = a[mid++];
        }

        while (low <= middle)
            work[j++] = a[low++];

        while (mid <= high)
            work[j++] = a[mid++];

        for (int i = 0; i < n; i++) {
            a[lower + i] = work[i];
        }
    }
}