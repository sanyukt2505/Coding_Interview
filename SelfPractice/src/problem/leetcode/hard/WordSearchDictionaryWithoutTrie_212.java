package problem.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WordSearchDictionaryWithoutTrie_212 {
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0)
            return res;

        ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
        /** iterate through all the char in array and  */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                /** if the first character matches, do dfs on  */
                dfs(board, i, j, wordList, res, "", new HashSet<String>());
            }
        }
        return res;
    }

    private static void dfs(char[][] board, int r, int c, List<String> words, List<String> res, String curr,
            HashSet<String> seen) {
        if (r < 0 || r > board.length-1 || c < 0 || c > board[0].length-1)
            return;

        if (board[r][c] == '*')
            return;

        char ch = board[r][c];
        if (words.contains(curr+ch) && !res.contains(curr+ch)) {
            seen.add(curr+ch);
            res.add(curr+ch);
        }

        board[r][c] = '*';
        dfs(board, r+1, c, words, res, curr+ch, seen);
        dfs(board, r-1, c, words, res, curr+ch, seen);
        dfs(board, r, c+1, words, res, curr+ch, seen);
        dfs(board, r, c-1, words, res, curr+ch, seen);
        // backtrack
        board[r][c] = ch;
    }

    public static void main(String[] args) {
        String[] dict = {"dam", "am", "a"};
        char input[][] = { {'a','b','c','d'},
                {'e','f','g','h'},
                {'i','j','k','l'},
                {'m','a','d','p'}
         };
//        char input[][] = { {'a'}
//        };

        for (String string : findWords(input, dict)) {
            System.out.println(string);
        }
    }
}
