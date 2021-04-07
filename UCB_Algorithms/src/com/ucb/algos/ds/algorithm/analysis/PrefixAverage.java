package com.ucb.algos.ds.algorithm.analysis;

/**
 * Created by Vijay on 2/12/16.
 */
public class PrefixAverage {
    public static void main(String[] args) {
        double[] numbers = {11.0,22.0,33.0,44.0,55.0};
        double[] prefixAverages = prefixAverage(numbers);
        for (double prefixAverage : prefixAverages) {
            System.out.print(prefixAverage + "\t");
        }
    }

    private static double[] prefixAverage(double[] numbers) {
        int n = numbers.length;
        double[] temp = new double[n];
        double sum = 0;
        for (int j = 0; j < n; j++) {
            sum += numbers[j];
            temp[j] = sum / (j+1);
        }
        return temp;
    }
}
