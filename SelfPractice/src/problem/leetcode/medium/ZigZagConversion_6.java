package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 */
public class ZigZagConversion_6 {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        // create SB for each row
        List<StringBuilder> strList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            strList.add(new StringBuilder());
        }

        boolean isDown = true;
        int row = 0;

        for (char c: s.toCharArray()) {
            strList.get(row).append(c);
            // when traversing down row++ else row--
            row += isDown ? 1: -1;
            if (row == 0 || row == numRows -1) {
                isDown = !isDown;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (StringBuilder sbRow: strList) {
            sb.append(sbRow);
        }
        return sb.toString();
    }
}
