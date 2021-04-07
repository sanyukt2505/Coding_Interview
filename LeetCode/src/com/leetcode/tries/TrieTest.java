package com.leetcode.tries;

/**
 * Created by Vijay on 4/24/16.
 */
public class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("somestring");

        System.out.println("Trie contains word World?: " + trie.search("key"));

        System.out.println("Trie contains word starting with W?: " + trie.startsWith("s"));
    }
}
