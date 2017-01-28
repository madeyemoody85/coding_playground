package linkedlist;

/**
 * Created by nachiketlondhe on 1/7/17.
 */
public class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return this.value;
    }
}
