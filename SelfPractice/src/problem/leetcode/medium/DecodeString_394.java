package problem.leetcode.medium;

import java.util.Stack;

/**
 * https://leetcode.com/problems/decode-string/
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being
 * repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 */
public class DecodeString_394 {
    public String decodeString(String s) {

        Stack<Integer> counts = new Stack();
        Stack<String> decoded = new Stack();
        String result = new String();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int count = 0;
                // if it is a digit, keep looping until you get to the full number. It can be either 3 or 30 or 305
                while(Character.isDigit(s.charAt(i))) {
                    count = count * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                counts.push(count);
            } else if (s.charAt(i) == '[') {
                //saving the result until now and creating a new string to hold the string between the []
                decoded.push(result);
                result = "";
            } else if (s.charAt(i) == ']') {
                // Looping the current string for the last value of count and appending it to previously saved result
                StringBuilder temp = new StringBuilder(decoded.pop());
                int count = counts.pop();
                for (int j = 0; j < count; j++) {
                    temp.append(result);
                }
                result = temp.toString();
            } else {
                result = result + (s.charAt(i));
            }
        }
        return result;
    }
}
