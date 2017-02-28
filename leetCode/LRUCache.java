package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Some simple implementation of the LRUCache
 */
public class LRUCache {

    int count;
    int capacity;

    Map<Integer, LRUNode> cache = new HashMap<>();

    LRUNode head;
    LRUNode tail;

    /**
     * Following are the methods associated LRUNode
     */

    class LRUNode {
        int key;
        int value;

        LRUNode prev;
        LRUNode next;
    }

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        this.head = new LRUNode();
        this.head.prev = null;

        this.tail = new LRUNode();
        this.tail.next = null;

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Add method for the doubly linked list always add the node
     * at the head of the list
     */
    public void addToHead(LRUNode node) {
        node.prev = this.head;
        node.next = this.head.next;

        this.head.next.prev = node;
        this.head.next = node;
    }

    /**
     * Remove method to remove the node from the list
     * First time you are writing this code
     */
    public void removeNode(LRUNode node) {
        LRUNode prev = node.prev;
        LRUNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    /**
     * Move the node to head of doubly linked list
     */
    public void moveToHead(LRUNode node) {
        this.removeNode(node);
        this.addToHead(node);
    }

    /**
     * Remove node from tail
     */
    public LRUNode removeFromTail() {
        LRUNode returnNode = this.tail.prev;
        this.removeNode(returnNode);
        return returnNode;
    }

    /**
     * Set method for the LRU cache
     */
    public void set(int key, int value) {
        LRUNode node = this.cache.get(key);

        // node doesn't exist in the cache
        if (node == null) {

            // create the node
            LRUNode temp = new LRUNode();
            temp.key = key;
            temp.value = value;

            // add the node to the map
            this.cache.put(key, temp);

            // now add the node to the doubly linked list
            this.addToHead(temp);

            this.count++;

            // get the node from the tail and the remove it
            // from the doubly linked list
            if (this.count > this.capacity) {
                LRUNode removed = this.removeFromTail();
                this.cache.remove(removed.key);
                this.count--;
            }

        } else {
            // update the existing value in the cache
            node.value = value;

            // we just trust the node move it to the head
            // of the list
            this.moveToHead(node);
        }
    }

    /**
     * Get method for the LRUCache
     */
    public int get(int key) {
        LRUNode node = this.cache.get(key);

        if (node == null) {
            return -1;
        }

        this.moveToHead(node);

        return node.value;
    }
}

