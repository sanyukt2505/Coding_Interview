package com.leetcode.tries;

/**
 * Design a data structure that supports the following two operations:
 *      void addWord(word)
 *      boolean search(word)
 *  search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 *  For example:
        addWord("bad")
        addWord("dad")
        addWord("mad")
        search("pad") -> false
        search("bad") -> true
        search(".ad") -> true
        search("b..") -> true
    Note:
        You may assume that all words are consist of lowercase letters a-z.

    This is an example of R-way Trie where R denotes length of character set we are considering (in this case, we are considering alphabet set whose length is 26)
 */
public class WordDictionary {
    class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String item = "";
    }

    private TrieNode root = new TrieNode();

    public void addWord(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }

        node.item = word;
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

    private boolean match(char[] chars, int k, TrieNode node) {
        if (k == chars.length) return !node.item.equals("");

        if (chars[k] != '.') {
            return node.children[chars[k] - 'a'] != null && match(chars, k + 1, node.children[chars[k] - 'a']);
        } else {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null) {
                    if (match(chars, k + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
