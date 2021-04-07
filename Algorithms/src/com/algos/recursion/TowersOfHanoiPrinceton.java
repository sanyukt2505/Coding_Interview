package com.algos.recursion;

/**
 * Created by Vijay on 4/24/16.
 */
public class TowersOfHanoiPrinceton {

    public static void main(String[] args) {
        int N = 3;
        moves(N, true);
    }

    private static void moves(int n, boolean left) {
        if (n == 0) return;

        moves(n-1, !left);

        if (left)   System.out.println(n + " left");
        else        System.out.println(n + " right");

        moves(n-1, !left);
    }
}
