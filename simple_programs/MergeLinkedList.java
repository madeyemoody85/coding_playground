package simple_programs;

public class MergeLinkedList {


    static class Node {
        int value;

        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static void main(String args[]) {

        Node list1 = new Node(3);
        list1.next = new Node(5);
        list1.next.next = new Node(7);
        list1.next.next.next = new Node(9);

        Node list2 = new Node(2);
        list2.next = new Node(4);
        list2.next.next = new Node(6);
        list2.next.next.next = new Node(8);
        list2.next.next.next.next = new Node(10);
        list2.next.next.next.next.next = new Node(12);

        Node merged = merge(list1, list2);

        System.out.println("Lists merged.");

    }

    public static Node merge(Node list1, Node list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        Node head = new Node(Integer.MIN_VALUE);
        Node newHead = head;

        while (list1 != null && list2 != null) {
            if (list1.value < list2.value) {
                newHead.next = list1;
                list1 = list1.next;
            } else {
                newHead.next = list2;
                list2 = list2.next;
            }
            newHead = newHead.next;
        }

        if (list1 != null) {
            newHead.next = list1;
        }

        if (list2 != null) {
            newHead.next = list2;
        }

        return head.next;
    }

}
