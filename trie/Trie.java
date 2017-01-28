package trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by nachiketlondhe on 1/24/16.
 * This is my very optimistic attempt to write trie
 */
public class Trie {

    static TrieNode root = null;

    public static void main(String args[]) throws IOException {

        Trie trie = new Trie();

        trie.createTree();

        BufferedReader br = new BufferedReader(new FileReader("/Users/nachiketlondhe/IdeaProjects/Interview Pro/src/trie/words.txt"));

        String currentLine;

        long startTime = System.currentTimeMillis();

        while ((currentLine = br.readLine()) != null) {
            trie.insertWord(root, currentLine.trim().toLowerCase());
        }

        System.out.println("Current Time : " + (System.currentTimeMillis() - startTime) + "ms");

        trie.printTree(root, 0, new char[50]);
    }

    public void createTree() {

        //Set the root to null char as its an empty string
        root = new TrieNode('\0');
    }

    public void insertWord(TrieNode root, String word) {

        char[] wordCharacters = word.toCharArray();

        int wordLength = word.length();

        //ASCII value of a is 97 so the
        int offset = 97;

        TrieNode currentNode = root;

        for(int i = 0; i < wordLength; i++ ) {

            if((wordCharacters[i]-offset) < 0){
                System.out.println(wordCharacters[i]);
            }

            if(currentNode.links[wordCharacters[i]-offset] == null) {

                currentNode.links[wordCharacters[i]-offset] = new TrieNode(wordCharacters[i]);

                currentNode = currentNode.links[wordCharacters[i]-offset];

            }
        }

        currentNode.isFullWord = true;
    }

    public void printTree(TrieNode root, int level, char[] branch)
    {
        if (root == null)
            return;

        for (int i = 0; i < root.links.length; i++)
        {
            branch[level] = root.letter;
            printTree(root.links[i], level + 1, branch);
        }

        if (root.isFullWord)
        {
            for (int j = 1; j <= level; j++)
                System.out.print(branch[j]);
            System.out.println();
        }
    }
}
