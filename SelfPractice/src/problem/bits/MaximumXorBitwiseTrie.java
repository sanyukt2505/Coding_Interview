package problem.bits;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1.... Find the maximum result of ai XOR aj, where 0 ≤ i, j < n
 * Input: [3, 10, 5, 25, 2, 8]
 * Output: 28
 * Explanation: The maximum result is 5 ^ 25 = 28.
 *
 * 1. Each root -> leaf path in Bitwise Trie represents a binary form of a number in nums, example, 0 -> 0 -> 0 -> 1-> 1 is 3.
 *    As before, the same number of bits LL is used for all numbers, and L = 1 1+[log2 M], where M is a maximum number
 *    in nums. The depth of Bitwise Trie is equal to LL as well, and all leafs are on the same level.
 * 2. To maximize XOR, the strategy is to choose the opposite bit at each step whenever it's possible.
 *
 * To summarise, now one could
 *      - Insert a number into Bitwise Trie.
 *      - Find maximum XOR of a given number with all numbers that have been inserted so far.
 */
public class MaximumXorBitwiseTrie {

    static class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        public TrieNode() {}
    }

    public static int findMaximumXOR(int[] nums) {
        // Compute length L of max number in a binary representation
        int maxNum = nums[0];
        for(int num : nums)
            maxNum = Math.max(maxNum, num);
        // converting to binary
        int L = (Integer.toBinaryString(maxNum)).length();

        // zero left-padding to ensure L bits for each number
        int n = nums.length, bitmask = 1 << L;
        String [] strNums = new String[n];
        for(int i = 0; i < n; ++i) {
            // appending extra 0 to binary value of short numbers to match the binary length of maxNum
            strNums[i] = Integer.toBinaryString(nums[i]);
            while (strNums[i].length() < L)
                strNums[i] = "0" + strNums[i];

            // strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);
        }

        TrieNode trie = new TrieNode();
        int maxXor = 0;
        for (String num : strNums) {
            TrieNode node = trie, xorNode = trie;
            String currXor = new String();
            for (Character bit : num.toCharArray()) {
                // insert new number in trie
                if (node.children.containsKey(bit)) {
                    node = node.children.get(bit);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(bit, newNode);
                    node = newNode;
                }

                // compute max xor of that new number with all previously inserted
                // if the opposite bit is present in Trie, we add 1 to the resultant Xor
                // else add one
                Character toggledBit = bit == '1' ? '0' : '1';
                if (xorNode.children.containsKey(toggledBit)) {
                    currXor = currXor + '1';
//                    currXor = (currXor << 1) | 1;
                    xorNode = xorNode.children.get(toggledBit);
                } else {
                    currXor = currXor + '0';
//                    currXor = currXor << 1;
                    xorNode = xorNode.children.get(bit);
                }
            }
            maxXor = Math.max(maxXor, Integer.parseInt(currXor, 2));
        }
        return maxXor;
    }

    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }

}
