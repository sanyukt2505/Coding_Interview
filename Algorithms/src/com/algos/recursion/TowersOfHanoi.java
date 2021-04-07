package com.algos.recursion;

/**
 * Refer to this video: https://www.udemy.com/algorithmic-problems-in-java/learn/v4/t/lecture/4071766
 */
public class TowersOfHanoi {
    public static void solveHanoiProblem(int n, char rodFrom, char middleRod, char rodTo) {

        if (n == 1) {
            System.out.println("Plate 1 from " + rodFrom + " to " + rodTo);
            return;
        }

        solveHanoiProblem(n-1, rodFrom, rodTo, middleRod);
        System.out.println("Plate " + n + " from " + rodFrom + " to " + rodTo);
        solveHanoiProblem(n-1, middleRod, rodFrom, rodTo);
    }

    public static void main(String[] args) {
        solveHanoiProblem(3, 'A', 'B', 'C');
    }

}
