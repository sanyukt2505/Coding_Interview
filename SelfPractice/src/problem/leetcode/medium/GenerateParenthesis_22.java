package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 */
public class GenerateParenthesis_22 {
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<String>(Arrays.asList(""));
        } else {
            HashSet<String> result = new HashSet();
            for (String str: generateParenthesis(n-1)) {
                result.add("()" + str);
                result.add(str + "()");
                result.add("(" + str + ")");
            }
            return new ArrayList(result);
        }
    }

    /**
     * Backtracking
     * Only add '(' or ')' when we know it will remain a valid sequence
     * We can start an opening bracket if we still have one (of n) left to place.
     * And we can start a closing bracket if it would not exceed the number of opening brackets.
     */
    public List<String> generateParenthesisBackTrack(int n) {
        List<String> result = new ArrayList();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    public void backtrack(List<String> result, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            result.add(cur);
            return;
        }

        if (open < max)
            backtrack(result, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(result, cur+")", open, close+1, max);
    }
}
