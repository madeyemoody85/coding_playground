package leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by nachiketlondhe on 3/16/16.

 Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 For example,
 Given words = ["oath","pea","eat","rain"] and board =

 [
 ['o','a','a','n'],
 ['e','t','a','e'],
 ['i','h','k','r'],
 ['i','f','l','v']
 ]
 Return ["eat","oath"].
 Note:
 You may assume that all inputs are consist of lowercase letters a-z.

 */

public class WordSearch {

    public static int CHAR_OFFSET = 97;

    /**
     * Class to store a trie node
     */
    public class TrieNode {

        TrieNode[] links;
        char letter;
        boolean isFullWord;

        public TrieNode(char letter) {

            this.letter = letter;
            this.links = new TrieNode[26];
            this.isFullWord = false;

        }
    }

    /**
     * Store the trie structure
     *
     * offset by 97
     */
    public class Trie {

        private TrieNode root;

        /**
         * Default constructor
         */
        public Trie() {
            root = new TrieNode('\0');
        }

        /**
         * Insert word in trie
         *
         * 1. Convert the word to char array
         * 2. store the root in current node,
         * 3. Starting with the first letter in the char array try inserting them into the root if they dont exist
         *
         *    Note: Characters position in the array is determined by subtracting the OFFSET value from the char value
         *    a is 97, so if the first letter of the word is a. it should be placed at the 0th position , b at 1 and so on
         *
         * 4. finally when the last letter is placed, mark the isFullWord to true
         *
         * @param word word to be entered in the trie
         */
        public void insertWord(String word) {

            if(word == null || word.isEmpty()) {
                System.out.println("Not a valid word");
                return;
            }

            TrieNode currentNode = root;

            for(char c: word.toCharArray()) {

                int charPosition = c - CHAR_OFFSET;

                if(charPosition < 0) {
                    System.out.println("We have a false letter: " + c);
                }

                if(currentNode.links[charPosition] == null) {
                    currentNode.links[charPosition] = new TrieNode(c);
                }

                currentNode = currentNode.links[charPosition];
            }

            currentNode.isFullWord = true;

        }

        /**
         * Following method will search trie to see if the prefix exists in the structure
         *
         * Note: If the prefix doesn't exist in trie there is not point in looking forward
         * @param prefix prefix to be searched
         * @boolean returns whether the prefix exists in trie or not
         */
        public boolean startsWithPrefix(String prefix) {

            if(prefix == null || prefix.isEmpty()) {
                System.out.println("Not a valid prefix");
                return false;
            }

            TrieNode currentNode = root;

            for(char c: prefix.toCharArray()) {

                if(currentNode.links[c - CHAR_OFFSET] == null) {
                    return false;
                }

                currentNode = currentNode.links[c - CHAR_OFFSET];
            }

            return true;
        }

        /**
         * Following method will search for a word in trie
         *
         * @param word word to be searched
         * @return returns whether the word exists in the trie or not
         */
        public boolean findWord(String word) {
            if(word == null || word.isEmpty()) {
                System.out.println("Not a valid word");
                return false;
            }

            TrieNode currentNode = root;

            for(char c: word.toCharArray()) {

                if(currentNode.links[c - CHAR_OFFSET] == null) {
                    return false;
                }

                currentNode = currentNode.links[c - CHAR_OFFSET];
            }

            if(currentNode.isFullWord) {
                return true;
            } else {
                return  false;
            }
        }
    }

    public List<String> findWords(char[][] board, String[] words) {

        //To ensure uniqueness
        HashSet<String> set = new HashSet();

        Trie trie = new Trie();

        for (int i=0; i < words.length; i++) {
            trie.insertWord(words[i]);
        }

        int boardWidth = board.length;
        int boardHeight = board[0].length;

        boolean[][] visited = new boolean[boardWidth][boardHeight];

        for (int i = 0 ; i < boardWidth; i ++) {
            for (int j = 0; j < boardHeight; j ++) {
                findWords(board, visited, "", i, j, trie, set);
            }
        }

        return new ArrayList<>(set);
    }

    /**
     * Helper method to find all the possible word,
     *
     * @param board board given to us
     * @param visited boolean 2D array to keep track of board positions we have visited
     * @param str string prefix to find, here the thinking is if the prefix doesn't exist in the board there is no
     *            point to continue the search
     * @param i horizontal position on the board
     * @param j vertical position on the baord
     * @param trie our trie which stores the board in the valid words
     * @param set set in which we will store the words
     */

    public void findWords(char[][] board, boolean[][] visited, String str, int i, int j, Trie trie, HashSet<String> set ) {

        int boardWidth = board.length;
        int boardHeight = board[0].length;

        //Return if out of bounds
        if ( i < 0 || j < 0 || i < boardWidth || j < boardHeight) {
            return;
        }

        //Return if previously visited
        if (visited[i][j]) {
            return;
        }

        //Append the char at current position to make the prefix
        str = str + board[i][j];

        //Check if the prefix exists in trie, return if not
        if (!trie.startsWithPrefix(str)) {
            return;
        }

        //If prefix is full word add it to the list
        if (trie.findWord(str)) {
            set.add(str);
        }

        //Mark the position visited before recurssion
        visited[i][j] = true;

        //GO TO TOWN ;)
        findWords(board, visited, str, i, j + 1, trie, set);
        findWords(board, visited, str, i, j - 1, trie, set);
        findWords(board, visited, str, i + 1, j, trie, set);
        findWords(board, visited, str, i - 1, j, trie, set);

        //Mark the position as not visited
        visited[i][j] = false;
    }
}
