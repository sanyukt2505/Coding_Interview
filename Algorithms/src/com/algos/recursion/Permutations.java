package com.algos.recursion;

/**
 * Created by Vijay on 4/24/16.
 */
public class Permutations {
    
    public static void main(String[] args) {
        int N = 3;
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String elements = alphabet.substring(0, N);
        perm1(elements);
    }

    private static void perm1(String s) {
        perm1("", s);
    }

    private static void perm1(String prefix, String s) {
        int N = s.length();
        if (N == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < N; i++) {
                perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, N));
            }
        }
    }
}
