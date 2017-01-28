package trie;

/**
 * Created by nachiketlondhe on 1/24/16.
 */
public class TrieNode {

    //letter stored at this node
    char        letter;

    //Possible links to the other characters alphabets, currently assuming all letters are lower case
    TrieNode[]  links;

    //Boolean indicating whether from root to this node is a full word or not
    boolean     isFullWord;

    public TrieNode(char letter) {

        this.letter = letter;
        this.links  = new TrieNode[26];
        isFullWord   = false;

    }
}
