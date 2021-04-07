package com.leetcode.arrays.strings;

import java.util.HashMap;

/**
 * Created by Vijay on 4/26/16.
 */
public class RomanToInt {
    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = map.get(s.charAt(len - 1));

        int pivot = result;

        for (int i = len - 2; i >= 0; i--) {
            int curr = map.get(s.charAt(i));
            if (curr >= pivot) {
                result += curr;
            } else {
                result -= curr;
            }
            pivot = curr;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("III: " + romanToInt("III"));
        System.out.println("IV: " + romanToInt("IV"));
        System.out.println("VI: " + romanToInt("VI"));
        System.out.println("XIX: " + romanToInt("XIX"));
    }

}
