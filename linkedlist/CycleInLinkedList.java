package linkedlist;

public class CycleInLinkedList {

    class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    /**
     * given the head of the linked list find out if there
     * is a cycle in the linked list
     */
    public boolean doesCycleExist(Node head) {
        if (head == null || head.next == null) return true;

        Node runner1 = head;
        Node runner2 = head;

        while (runner2.next != null) {
            runner1 = runner1.next;
            runner2 = runner2.next.next;

            if (runner1 == runner2) {
                return true;
            }
        }

        // default case is false
        return false;
    }

    /**
     * given a linked list find the nth to the last node
     */
    public Node findNthNode(Node head, int n) {
        if (head == null) return null;

        Node slowRunner = head;
        Node diffRunner = head;

        for (int i = 0; i < 4; i++) {
            if (diffRunner.next != null) {
                throw new IllegalArgumentException("N is out of bounds");
            }
            diffRunner = diffRunner.next;
        }

        while (diffRunner != null) {
            slowRunner = slowRunner.next;
            diffRunner = diffRunner.next;
        }

        return slowRunner;
    }
}
