package leetCode;

/**
 *          10
 *      8         16
 *   5     9   14       17
 * 3
 * Created by nachiketlondhe on 2/24/17.
 */
public class TreeToList {

    private static Node root;
    class Node {
        int value;
        Node prev;
        Node next;

        public Node(int value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    public void insertNode(int data) {
        if(root == null) {
            root = new Node(data);
            root.prev = null;
            root.next = null;
        }

        Node temp = root;

        while(temp != null) {
            if (data < temp.value) {
                if (temp.prev == null) {
                    temp.prev = new Node(data);
                    break;
                }
                temp = temp.prev;
            } else if (data > temp.value) {
                //Go right
                if (temp.next == null) {
                    temp.next = new Node(data);
                    break;
                }
                temp = temp.next;
            } else {
                break;
            }
        }
    }

    public void join(Node a, Node b) {
        a.next = b;
        b.prev = a;
    }

    public Node append(Node list1, Node list2) {
        if (list1 == null) return list2;

        if (list2 == null) return list1;

        Node temp = list1;

        while (temp.next != null) {
            temp = temp.next;
        }

        join(temp, list2);

        return list1;
    }

    public Node treeToList(Node root) {
        if (root == null) return null;

        Node left = treeToList(root.prev);
        Node right = treeToList(root.next);

        if (left == null && right == null) return root;

        // null out root
        root.prev = null;
        root.next = null;

        left = append(left, root);
        left = append(left, right);

        return left;
    }

    public static void main(String args[]) {
        TreeToList tree = new TreeToList();

        tree.insertNode(10);
        tree.insertNode(8);
        tree.insertNode(16);
        tree.insertNode(5);
        tree.insertNode(9);
        tree.insertNode(14);
        tree.insertNode(17);
        tree.insertNode(3);
        tree.insertNode(2);
        tree.insertNode(1);

        Node list = tree.treeToList(root);

        while (list != null) {
            System.out.print(list.value + " -> ");
            list = list.next;
        }

        System.out.println();
    }
}
