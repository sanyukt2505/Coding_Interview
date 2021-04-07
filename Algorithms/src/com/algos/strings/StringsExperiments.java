package com.algos.strings;

/**
 * Created by Vijay on 2/19/16.
 */
public class StringsExperiments {
    public static void main(String[] args) {
        String test = "Malayalam";
        int strLength = test.length();

        for (int i = 0; i < strLength; i += 1) {
            System.out.print(test.charAt(i) + "\t");
        }
    }
}
