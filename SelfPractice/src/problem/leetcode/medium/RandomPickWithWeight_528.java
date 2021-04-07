package problem.leetcode.medium;

import java.util.Random;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/random-pick-with-weight/
 * You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).
 * We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1].
 * pickIndex() should return the integer proportional to its weight in the w array.
 * For example, for w = [1, 3], the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%)
 * while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).
 *
 * More formally, the probability of picking index i is w[i] / sum(w).
 * Input
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output
 * [null,1,1,1,1,0]
 */
public class RandomPickWithWeight_528 {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    Random rand = new Random();
    int sum = 0;

    public RandomPickWithWeight_528(int[] w) {
        int sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            map.put(sum, i);
        }
    }

    public int pickIndex() {
        int randIndex = rand.nextInt(sum);
        return map.get(map.ceilingKey(randIndex));
    }
}
