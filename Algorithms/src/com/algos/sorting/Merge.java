package com.algos.sorting;

public class Merge {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // Condition is that everything in the array a from lo to mid is sorted
        assert isSorted(a, lo, mid);

        // Also, everything from mid+1 to high is sorted
        assert isSorted(a, mid + 1, hi);

        // Copy all elements within lo to hi range to auxiliary array
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // We are now set up for assignments and a for loop that accomplishes the merge sort
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            }
            else if (j > hi) {
                a[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            }
            else {
                a[k] = aux[i++];
            }
        }
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        // Base condition: If hi <= lo stop
        if (hi <= lo) return;

        // Calculate mid
        int mid = lo + (hi - lo) / 2;

        // Recursively sort left and right subarrays
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);

        // merge two sorted arrays
        merge(a, aux, lo, mid, hi);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) {
        Integer[] aux = new Integer[a.length];

        sort(a, aux, 0, a.length - 1);
    }

    public static void main(String[] args) {
        Integer[] arr = {24, 30, 21, 15, 27};

        sort(arr);

        for (Integer i : arr) {
            System.out.println(i);
        }
    }


}
