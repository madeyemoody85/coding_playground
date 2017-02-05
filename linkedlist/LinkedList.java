package linkedlist;

import java.util.Stack;

/**
 * This got me in the head last time but not this time. ALL BY MYSELF BITCHES !!!
 * Created by nachiketlondhe on 1/7/17.
 */
public class LinkedList {
    private static Node head;

    public static void main(String args[]) {

        LinkedList list = new LinkedList();

        for(int i=0; i < 10; i++) {
            list.addNode(i);
        }

        list.reverseList();
        list.printList(list.getHead());
        list.reverseList();
        list.printList(list.getHead());
        list.printReverseListUsingStack();
        list.deleteNode(6);
        list.printList(list.getHead());
        list.deleteNode(0);
        list.printList(list.getHead());
    }

    public void addNode(int value) {
        if(head == null) {
            head = new Node(value);
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        Node newNode = new Node(value);
        temp.next = newNode;
    }

    public void printList(Node head) {
        Node temp = head;
        while (temp.next != null) {
            System.out.print(temp.getValue() + " --> ");
            temp = temp.next;
        }
        System.out.print(temp.getValue() + "\n");
    }

    public void deleteNode(int value) {
        if(head  == null) {
            System.out.println("Empty list");
            return;
        }
        if(head.getValue() == value) {
            head = head.next;
        } else {
            Node prev = head;
            Node next = head.next;
            while(next != null) {
                if (next.getValue() == value) {
                    prev.next = next.next;
                    return;
                } else {
                    prev = next;
                    next = next.next;
                }
            }
        }
    }

    public Node reverseListOld() {
        if(head == null || head.next == null) {
            return head;
        }

        Node prev = null;
        Node current = head;
        Node next = head.next;

        while(current != null) {
            current.next = prev;
            prev = current;
            current = next;
            if(current != null) {
                next = current.next;
            }
        }

        head = prev;
        return head;
    }

    public Node reverseList() {
        if (head == null || head.next == null) {
            System.out.println("Possibly empty list...");
        }

        Node prev = null;
        Node current = head;
        Node next = head.next;

        while (current != null) {
            current.next = prev;
            prev = current;
            current = next;
            if (current != null ) {
                next = current.next;
            }
        }

        head = prev;
        return head;
    }

    public void printReverseListUsingStack() {
        if(head == null) {
            return;
        }
        if(head.next == null) {
            System.out.println(head.getValue());
        }

        Stack stack = new Stack<>();
        Node temp = head;
        while(temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while(!stack.empty()) {
            Node print = (Node)stack.pop();
            System.out.print(print.getValue() + " --> ");
        }
        System.out.println();
    }

    public Node getHead() {
        return head;
    }
}
