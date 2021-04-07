package com.ucb.algos.ds.fundamental.ds;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Vijay on 2/7/16.
 */
public class ArrayTest {
    public static void main(String[] args) {
        int data[] = new int[10];
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for(int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(100);
        }

        int[] orig = Arrays.copyOf(data, data.length);
        System.out.println("arrays equal before sort: "+Arrays.equals(data, orig));
        Arrays.sort(data);
        System.out.println("arrays equal before sort: "+Arrays.equals(data, orig));
        System.out.println("orig: "+Arrays.toString(orig));
        System.out.println("data: "+Arrays.toString(data));
    }
}
