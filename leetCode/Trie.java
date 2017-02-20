package leetCode;

/**
 * Created by nachiketlondhe on 2/20/17.
 */
public class Trie {

    class TrieNode {
        char letter;
        TrieNode[] links;
        boolean isFullWord;

        public TrieNode(char c) {
            this.letter = c;
            this.links = new TrieNode[26];
            this.isFullWord = false;
        }
    }

    private TrieNode root;
    private int OFFSET = 97;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode('\0');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] charArray = word.toCharArray();
        TrieNode current = root;
        for (int i = 0; i < charArray.length; i++) {
            int charPosition = charArray[i] - OFFSET;
            if (current.links[charPosition] == null) {
                TrieNode node = new TrieNode(charArray[i]);
                current.links[charPosition] = node;
            }
            current = current.links[charPosition];
        }
        current.isFullWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.isEmpty()) {
            return true;
        }
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int charPosition = c - OFFSET;
            if (current.links[charPosition] == null) {
                return false;
            }
            current = current.links[charPosition];
        }
        if (current.isFullWord) {
            return true;
        }
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return false;
        }
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            int charPosition = c - OFFSET;
            if (current.links[charPosition] == null) {
                return false;
            }
            current = current.links[charPosition];
        }
        return true;
    }
}
