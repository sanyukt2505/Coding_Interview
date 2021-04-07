package problem.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/text-justification/
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 */
public class TextJustify_68 {
    /**
     * Use List to store a line so we can add space after each word without creating a new string.
     * Iterate through each word, add to the current line, if current line is full, format the current line and add word to next line.
     * In #2, a line is fully justified when the next word does not fit the line. In other words,
     * the current line won't be fully justified until we process the next word.
     * Since the last line does not have the next word, it won't be fully justified.
     * This fits the requirement perfectly because the last line requires a different format, left justification.
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        List<StringBuilder> line = new ArrayList<>();
        int totLetters = 0;
        for(String word: words) {
            int len = word.length();
            int size = line.size();
            if(totLetters + size + len > maxWidth) {
                int space = maxWidth - totLetters;
                if(size == 1) {
                    res.add(line.get(0).toString()+  getSpace(" ", space));
                } else {
                    int spc = space/(size-1);
                    int r = space % (size-1);
                    for(int i = 0; i < r; i++)
                        line.get(i).append(" ");
                    res.add(String.join(getSpace(" ", spc), line));
                }
                totLetters = 0;
                line.clear();
            }
            totLetters += len;
            line.add(new StringBuilder(word));
        }
        String last = String.join(" ",line);
        res.add(String.format("%-"+maxWidth+"s",last));
        return res;
    }

    private String getSpace(String s, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< count; i++)
            sb.append(s);

        return sb.toString();
    }
}
