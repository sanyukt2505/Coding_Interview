package problem.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/expression-add-operators/
 * Given a string that contains only digits 0-9 and a target value, return all possibilities
 * to add binary operators (not unary) +, - between the digits so they evaluate to the target value.
 * 123 + 4 - 5 + 67 - 89 = 100
 */
public class ExpressionAddOperators_282 {
    public ArrayList<String> answer;
    public String digits;
    public long target;

    /** This handle + - and *
     * Input: num = "123", target = 6
     * Output: ["1+2+3", "1*2*3"]
     **/
    public List<String> addOperators(String num, int target) {
        if (num.length() == 0) {
            return new ArrayList<String>();
        }

        this.target = target;
        this.digits = num;
        this.answer = new ArrayList<String>();
        this.recurse(0, 0, 0, 0, new ArrayList<String>());
        return this.answer;
    }

    public void recurse(int index, long previousOperand, long currentOperand, long value, ArrayList<String> ops) {
        String nums = this.digits;

        // Done processing all the digits in num
        if (index == nums.length()) {

            // If the final value == target expected AND no operand is left unprocessed
            if (value == this.target && currentOperand == 0) {
                StringBuilder sb = new StringBuilder();
                ops.subList(1, ops.size()).forEach(v -> sb.append(v));
                this.answer.add(sb.toString());
            }
            return;
        }

        // Extending the current operand by one digit
        currentOperand = currentOperand * 10 + Character.getNumericValue(nums.charAt(index));
        String current_val_rep = Long.toString(currentOperand);
        int length = nums.length();

        // To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
        // valid operand. Hence this check
        if (currentOperand > 0) {
            // NO OP recursion
            recurse(index + 1, previousOperand, currentOperand, value, ops);
        }

        // ADDITION
        ops.add("+");
        ops.add(current_val_rep);
        recurse(index + 1, currentOperand, 0, value + currentOperand, ops);
        ops.remove(ops.size() - 1);
        ops.remove(ops.size() - 1);

        if (ops.size() > 0) {

            // SUBTRACTION
            ops.add("-");
            ops.add(current_val_rep);
            recurse(index + 1, -currentOperand, 0, value - currentOperand, ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);

            // MULTIPLICATION
            ops.add("*");
            ops.add(current_val_rep);
            recurse(index + 1,currentOperand * previousOperand, 0,
                    value - previousOperand + (currentOperand * previousOperand), ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);
        }
    }

    /**
     * Handle only + and -
     * 123 + 4 - 5 + 67 - 89 = 100
     */
    public static List<List<Integer>> findSum(int target) {
        List<List<Integer>> res = new ArrayList<>();
        findSumHelper("123456789", new ArrayList<>(), target, res);
        return res;
    }

    /**
     * DFS Java version, the basic idea here is to divide string into 2 parts:
     * [consider number] + remaining string, with the consider number,
     * we have 2 choices, proceed with + or -, and with each choice, recursively call with remaining string.
     * The base case here is remaining string is empty and target == 0 we find the expression:
     */
    private static void findSumHelper(String txt, List<Integer> chosen, int target, List<List<Integer>> res) {
        if (txt.isEmpty() && target == 0) {
            res.add(new ArrayList<>(chosen));
            return;
        }

        if (!txt.isEmpty()) {
            for (int i = 1; i <= txt.length(); i++) {
                String s1 = txt.substring(0, i);
                String s2 = txt.substring(i);

                // explore with +
                int x = Integer.valueOf(s1);
                chosen.add(x);
                findSumHelper(s2, chosen, target - x, res);
                chosen.remove(chosen.size() - 1);

                // explore with -
                int y = -x;
                chosen.add(y);
                findSumHelper(s2, chosen, target - y, res);
                chosen.remove(chosen.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(findSum(100));
    }

}
