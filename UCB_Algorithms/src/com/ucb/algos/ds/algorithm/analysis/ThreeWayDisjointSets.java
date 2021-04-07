package com.ucb.algos.ds.algorithm.analysis;

import java.util.Arrays;

/**
 * Created by Vijay on 2/12/16.
 */
public class ThreeWayDisjointSets {
    public static void main(String[] args) {
        double[] setOne = {11.0, 22.0, 33.0};
        double[] setTwo = {44.0, 66.0, 55.0};
        double[] setThree = {99.0, 88.0, 77.0};
        boolean isThreeWayDisjointSet = isThreeWayDisjointSet(setOne, setTwo, setThree);
        System.out.println("Is three way disjoint set: "+isThreeWayDisjointSet);
    }

    private static boolean isThreeWayDisjointSet(double[] setOne, double[] setTwo, double[] setThree) {
        // Calculate total length of all sets
        int length = setOne.length + setTwo.length + setThree.length;

        // Declare an array of size total length calculated above
        double[] mixedSet = new double[length];

        // Copy all arrays into the mixedSet array
        int index = 0;
        for (int i = 0; i < setOne.length; i++) {
            mixedSet[index] = setOne[i];
            index++;
        }
        for (int j = 0; j < setTwo.length; j++) {
            mixedSet[index] = setTwo[j];
            index++;
        }
        for (int k = 0; k < setThree.length; k++) {
            mixedSet[index] = setThree[k];
            index++;
        }

        // Sort the Array
        Arrays.sort(mixedSet);

        // If two elements in the
        for (int l = 1; l < length-1; l++) {
            if (mixedSet[l-1] == mixedSet[l]) return false;
        }
        return true;
    }
}
