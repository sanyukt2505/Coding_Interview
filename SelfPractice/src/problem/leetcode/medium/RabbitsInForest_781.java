package problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/rabbits-in-forest/
 * https://www.youtube.com/watch?v=_-covKlf0Rk
 * In a forest, each rabbit has some color. Some subset of rabbits (possibly all of them) tell you how many
 * OTHER rabbits have the same color as them. Those answers are placed in an array.
 *
 * Return the minimum number of rabbits that could be in the forest.
 * Input: answers = [1, 1, 2]       Output: 5
 */
public class RabbitsInForest_781 {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int total = 0;

        // creating an array of the count
        for (int ans: answers)
            countMap.put(ans, countMap.getOrDefault(ans, 0) + 1);

        /** when a rabbit tells, how many other rabbits have the same color as them,
        // that mean at least, numOfRabbits with that color = answer + himself
        // answer+1 = the size of the color group   */
        for (Map.Entry<Integer, Integer> entry: countMap.entrySet()) {
            /** creating groups of size = entry.getKey()+1 */
            total += (entry.getValue() / (entry.getKey()+1)) * (entry.getKey()+1) ;      //find number of rabbit groups

            /** consider 3 rabbits said 1, so on this case 2 of them of same color and third will be of another color */
            if (entry.getValue() % (entry.getKey()+1) > 0) {
                total += (entry.getKey()+1);                       //account for rabbits that didn't answer
            }
        }

        return total;
    }
}
