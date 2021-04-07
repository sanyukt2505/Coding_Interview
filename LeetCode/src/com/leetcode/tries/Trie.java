package com.leetcode.tries;

/**
 *  Implement a trie with insert, search, and startsWith methods.
 *
 *  Note: You may assume that all inputs are consist of lowercase letters a-z.
 *
 *  This is an example of R-way Trie where R denotes length of character set we are considering (in this case, we are considering alphabet set whose length is 26)
 */

class TrieNode {
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];

    public TrieNode() {}
}

public class Trie {
    private TrieNode root;

    public Trie() {
        // root of the Trie is an empty word
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode ws = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (ws.children[c - 'a'] == null) {
                ws.children[c - 'a'] = new TrieNode();
            }

            ws = ws.children[c - 'a'];
        }

        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }

        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }

        return true;
    }
}
