package com.algos.sorting;

/**
 * Created by Vijay on 1/29/16.
 */
public class InsertionSortTest {
    public static void main(String[] args) {
        String[] strArr = {"C", "O", "M", "P", "A", "R", "E"};
        Insertion.sort(strArr);
        for (String str : strArr) {
            System.out.print(str + "\t");
        }
    }
}
