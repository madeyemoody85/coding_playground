package binary_tree;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by nachiketlondhe on 1/23/16.
 */
public class BinarySearchTree {

    private static Node root = null;

    class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {

            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String args[]) {

        //Tree Object
        BinarySearchTree tree = new BinarySearchTree();

        System.out.println("Creating tree: ");

        /*int[] values = {2, 3, 1};

        //Add the values to the tree
        for(int i: values) {
            tree.addValueToTree(i);
        }

        tree.printTree();

        System.out.println(System.lineSeparator());
        System.out.println(System.lineSeparator());

        ArrayList<Integer> inorderTraversal = tree.inorderTraversal(tree.getRoot());

        for(Integer i: inorderTraversal) {
            System.out.print(i + " ");
        }

        System.out.println(System.lineSeparator());
        System.out.println(System.lineSeparator());

        ArrayList<Integer> preOrderTraversal = tree.preorderTraversal(tree.getRoot());

        for(Integer i: preOrderTraversal) {
            System.out.print(i + " ");
        }

        System.out.println(System.lineSeparator());
        System.out.println(System.lineSeparator());

        ArrayList<Integer> postOrderTraversal = tree.postOrderTraversal_(tree.getRoot());

        for(Integer i: postOrderTraversal) {
            System.out.print(i + " ");
        }*/

        tree.addValueToTree(3);
        tree.addValueToTree(1);
        tree.addValueToTree(4);
        tree.addValueToTree(2);

        tree.delete(root, 3);
    }

    public void addValueToTree(int data) {

        if(root == null) {
            root = new Node(data);
            root.left = null;
            root.right = null;
        }

        Node temp = root;

        while(temp != null) {
            if (data < temp.data) {
                if (temp.left == null) {
                    temp.left = new Node(data);
                    break;
                }

                temp = temp.left;

            } else if (data > temp.data) {
                //Go right
                if (temp.right == null) {
                    temp.right = new Node(data);
                    break;
                }

                temp = temp.right;
            } else {
                break;
            }
        }
    }

    public Node delete(Node root, int val) {
        if (root == null) return null;

        Node parent = null;
        Node temp = root;

        boolean is_left = true;

        // find the node
        while (temp != null){
            if (val == temp.data) {
                break;
            } else if (val < temp.data) {
                parent = temp;
                is_left = true;
                temp = temp.left;
            } else {
                parent = temp;
                is_left = false;
                temp = temp.right;
            }
        }

        if (temp == null) {
            return root;
        }

        // temp is the node you have found

        // case 1: this is a leaf node
        if (temp.left == null && temp.right == null) {
            if (parent == null) {
                return null;
            } else {
                if (is_left) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }

        } else if (temp.left == null || temp.right == null) {
            // case 2: this has exactly only one child
            Node child;

            if (temp.left != null) {
                child = temp.left;
            } else {
                child = temp.right;
            }

            if (parent == null) {
                root = child;
            } else {
                if (is_left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
        } else {
            // case 3: this one has two children
            Node parent_repl = temp;
            Node replacement = temp.left;

            is_left = true;
            while (replacement.right != null) {
                parent_repl = replacement;
                replacement = replacement.right;
                is_left = false;
            }

            temp.data = replacement.data;

            if (is_left) {
                temp.left = replacement.left;
            } else {
                parent_repl.right = replacement.left;
            }
        }

        return root;
    }

    public void printTree() {

        if(root == null) {
            System.out.println("Empty tree:");
        }

        Queue queue = new LinkedBlockingQueue<>();

        Node temp = root;

        queue.add(temp);

        while(temp != null) {

            System.out.print(temp.data + " ");

            if(temp.left != null) {
                queue.add(temp.left);
            }
            if(temp.right != null) {
                queue.add(temp.right);
            }

            queue.remove();

            temp = (Node) queue.peek();
        }
    }

    public ArrayList<Integer> inorderTraversal(Node root) {

        if(root == null) {
            System.out.println("Empty tree found.");
            return new ArrayList<>();
        }

        ArrayList listToReturn = new ArrayList();

        Stack<Node> stack = new Stack();

        Node p = root;

        while(!stack.empty() || p != null) {

            if(p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                listToReturn.add(p.data);
                p = p.right;
            }
        }
        return listToReturn;
    }

    public ArrayList<Integer> preorderTraversal(Node root) {

        if(root == null){
            System.out.println("Empty tree found.");
            return new ArrayList<>();
        }

        ArrayList<Integer> listToReturn = new ArrayList<>();

        Stack<Node> stack = new Stack<>();

        stack.push(root);

        while(!stack.empty()) {

            Node current = stack.pop();

            listToReturn.add(current.data);

            if(current.right != null) {

                stack.push(current.right);

            }

            if(current.left != null) {

                stack.push(current.left);

            }

        }

        return listToReturn;
    }


    public ArrayList<Integer> postOrderTraversal(Node root) {

        if(root == null) {
            System.out.println("Empty tree found. ");
            return  new ArrayList<>();
        }

        ArrayList<Integer> listToReturn = new ArrayList<>();

        Stack<Node> stack = new Stack<>();

        stack.push(root);

        Node prev = null;

        while(!stack.empty()) {

            Node current = stack.peek();

            //Go all the way to the left
            if(prev == null || prev.left == current || prev.right == current) {

                if(current.left != null) {
                    stack.push(current.left);
                } else if(current.right != null) {
                    stack.push(current.right);
                } else {
                    stack.pop();
                    listToReturn.add(current.data);
                }

            //Go back up the left node
            } else if(current.left == prev) {

                if(current.right != null) {
                    stack.push(current.right);
                } else {
                    stack.pop();
                    listToReturn.add(current.data);
                }

            //Go back up from the right node
            } else if(current.right == prev) {
                stack.pop();
                listToReturn.add(current.data);
            }

            prev = current;
        }

        return listToReturn;
    }

    public ArrayList<Integer> postOrderTraversal_(Node root) {

        ArrayList<Integer> listToReturn = new ArrayList<>();

        if(root == null) {
            return  listToReturn;
        }

        Stack<Node> stack = new Stack<>();

        stack.push(root);

        Node prev = null;

        while(!stack.empty()) {

            //Peek at the stack
            Node current = stack.peek();

            System.out.println("Value of current: " + current.data);

            //Go all the way to the left
            if(prev == null || prev.left == current || prev.right == current) {

                if(current.left != null) {
                    stack.push(current.left);
                } else if(current.right != null) {
                    stack.push(current.right);
                } else {
                    stack.pop();
                    listToReturn.add(current.data);
                }

            //Come back up from the left, here prev would be the child node of current
            } else if(current.left == prev) {

                if(current.right != null) {
                    stack.push(current.right);
                } else {
                    stack.pop();
                    listToReturn.add(current.data);
                }
            } else if(current.right == prev) {

                stack.pop();
                listToReturn.add(current.data);
            }

            prev = current;
            System.out.println("Value of prev: " + prev.data);
        }

        return listToReturn;
    }


    public Node getRoot() {
        return root;
    }
}
