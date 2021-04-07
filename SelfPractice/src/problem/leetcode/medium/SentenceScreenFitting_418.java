package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/sentence-screen-fitting/
 * Given a rows x cols screen and a sentence represented by a list of non-empty words,
 * find how many times the given sentence can be fitted on the screen.
 * Note:
 * A word cannot be split into two lines.
 * The order of words in the sentence must remain unchanged.
 * Two consecutive words in a line must be separated by a single space.
 * Total words in the sentence won't exceed 100.
 * Input:       rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
 * Output:  2
 * Explanation:
 *      a-bcd-
 *      e-a---
 *      bcd-e-
 */
public class SentenceScreenFitting_418 {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        StringBuilder str = new StringBuilder();
        /** create a full string of all d words  [“abc”, “de”, “f”] → String s: "abc de f "*/
        for (String s : sentence) {
            s = s + " ";
            str.append(s);
        }

        // filled is basically the number of spots that we have filled with our full string
        int filled = 0;
        for (int i = 0; i < rows; i++) {
            /** add to filled the number of column that needs to be filled  */
            filled = filled + cols;
            /**  if my last filled spot is " ", then I can assume that all the spots are filled correctly */
            if (str.charAt(filled % str.length()) == ' ') {
                filled++;
            } else {
                /**  if my last filled spot is not " ", then go reverse to find the last ' " */
                while (filled > 0 && str.charAt((filled-1) % str.length())  != ' ') {
                    filled--;
                }
            }
        }
        /** at last filled will have total number of spots that is filled using the full string
         * so number of sentence = filled / str.length */
        return filled / str.length();
    }
}
