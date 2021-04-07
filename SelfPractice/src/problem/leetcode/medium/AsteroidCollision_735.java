package problem.leetcode.medium;

import java.util.Stack;

/**
 * https://leetcode.com/problems/asteroid-collision/
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right,
 * negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * Input: asteroids = [10,2,-5]         Output: [10]
 * Input: asteroids = [5,10,-5]         Output: [5,10]
 */
public class AsteroidCollision_735 {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid: asteroids) {
            // add all positive asteroid to stack, until you get a negative asteroid
            if (asteroid > 0) {
                stack.push(asteroid);
            } else {
                // simulate the collision
                // check if there is an asteroid coming in this direction
                // check that the incoming asteroid value is small in size
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroid)) {
                    stack.pop();
                }

                // if all asteroid in stack are destroyed or it finds a asteoid moving in same direction
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteroid);
                } else if (stack.peek() == Math.abs(asteroid)) {
                    stack.pop();
                }
            }
        }
        int[] res = new int[stack.size()];
        for (int i = stack.size()-1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
