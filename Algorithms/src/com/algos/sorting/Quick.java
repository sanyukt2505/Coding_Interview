package com.algos.sorting;

public class Quick {

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;

        while (true) {
            // Notice: ++i is used. Reason: a[i] (in fact a[lo]) is used as a comparator
            // increment i until elements in array are greater than comparator
            while (less(a[++i], a[lo]))
                if (i == hi) break;

            // decrement j until comparator element at j is higher than element at lo
            while (less(a[lo], a[--j]))
                if (j == lo) break;

            // break when i crosses j
            if (i >= j) break;

            // exchange i, j
            exch(a, i, j);
        }

        // exchange the comparator element with j
        exch(a, lo, j);

        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length -1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static void main(String[] args) {
        Integer[] arr = {24, 30, 21, 15, 27};

        sort(arr);

        for (Integer i : arr) {
            System.out.println(i);
        }
    }
}
