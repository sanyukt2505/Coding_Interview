package com.algos.recursion;

public class HouseBuilding {

    public static void buildLayersTailRecur(int height) {
        if (height == 0) return;

        System.out.println(height);

        // tail recursion
        buildLayersTailRecur(height - 1);
    }

    public static void buildLayersHeadRecur(int height) {
        if (height == 0) return;

        // tail recursion
        buildLayersHeadRecur(height - 1);

        System.out.println(height);
    }

    public static void main(String[] args) {
        System.out.println("Tail Recursion Result: ");
        buildLayersTailRecur(4);

        System.out.println();

        System.out.println("Tail Recursion Result: ");
        buildLayersHeadRecur(4);
    }
}
