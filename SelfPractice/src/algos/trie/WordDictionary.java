package algos.trie;

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
 *
 *  This is an example of R-way Trie where R denotes length of character set we are considering (in this case, we are considering alphabet set whose length is 26)
 */
public class WordDictionary {

    public static final int ALPHA_SIZE = 26;

    public static class TrieNode {
        // In case of Dictionary we replace boolean endOfWord with String finalWord;
        public String finalWord = "";
        public TrieNode[] children = new TrieNode[ALPHA_SIZE];

        public TrieNode() {
        }
    }

    public TrieNode root;

    public void insert(String word) {
        TrieNode curr = root;
        int index;

        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';

            if(curr.children[index] == null)
                curr.children[index] = new TrieNode();

            curr = curr.children[index];
        }
        curr.finalWord = word;          // mark last node as leaf
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

    private boolean match(char[] chars, int k, TrieNode node) {
        if(k == chars.length)
            return !node.finalWord.equals("");

        if (chars[k] != '.') {
            return (node.children[chars[k] - 'a'] != null && match(chars, k + 1, node.children[chars[k] - 'a']));
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

    public static void main(String args[])
    {
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"hello", "there", "me", "heat"};

        WordDictionary trie = new WordDictionary();
        trie.root = new TrieNode();

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            trie.insert(keys[i]);

        // Search for different keys
        System.out.println("is present ? " + "he.: " + trie.search("he."));
        System.out.println("is present ? " + "he..: " + trie.search("he.."));
        System.out.println("is present ? " + "he...: " + trie.search("he..."));
        System.out.println("is present ? " + "the.: " + trie.search("the."));
        System.out.println("is present ? " + "..eir: " + trie.search("..eir"));
        System.out.println("is present ? " + "..ere: " + trie.search("..ere"));

    }

}
