package problem.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/word-search-ii/
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
 * vertically neighboring. The same letter cell may not be used more than once in a word.
 * Input: board = [ ["o","a","a","n"],
 *                  ["e","t","a","e"],
 *                  ["i","h","k","r"],
 *                  ["i","f","l","v"]],
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 */
public class WordSearchInDictionary_212 {
    class TrieNode {
        TrieNode[] children;
        String word;
        public TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0)
            return res;

        /** build Trie using the words provided */
        TrieNode root = new TrieNode();
        buildTrie(root, words);

        /** iterate through all the char in array and  */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                /** if the first character matches, do dfs on  */
                if (root.children[c-'a'] != null) {
                    dfs(board, i, j, root, res);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] board, int r, int c, TrieNode curr, List<String> res) {
        if (r < 0 || r > board.length-1 || c < 0 || c > board[0].length-1)
            return;

        if (board[r][c] == '*')
            return;

        char ch = board[r][c];
        if (curr.children[ch - 'a'] == null)
            return;

        curr = curr.children[ch - 'a'];
        /** if the child is present and there is word at that level -- its a match*/
        if(curr.word != null){
            res.add(curr.word);
            /** to avoid duplicates getting added */
            curr.word = null;
        }

        board[r][c] = '*';
        dfs(board, r+1, c, curr, res);
        dfs(board, r-1, c, curr, res);
        dfs(board, r, c+1, curr, res);
        dfs(board, r, c-1, curr, res);
        // backtrack
        board[r][c] = ch;
    }

    public void buildTrie(TrieNode root, String[] words) {
        for (String s: words) {
            TrieNode curr = root;

            for (char c: s.toCharArray()) {
                int index = c - 'a';
                if(curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.word = s;
        }
    }
}
