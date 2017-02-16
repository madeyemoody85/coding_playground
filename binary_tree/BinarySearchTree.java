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

        tree.addValueToTree(5);
        tree.addValueToTree(2);
        tree.addValueToTree(12);
        tree.addValueToTree(-4);
        tree.addValueToTree(3);
        tree.addValueToTree(9);
        tree.addValueToTree(21);
        tree.addValueToTree(7);
        tree.addValueToTree(19);
        tree.addValueToTree(25);

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

    public void delete(Node root, int value) {
        if (root == null) {
            return;
        } else {
            Node temp = root;
            Node parent = null;
            while (temp != null) {
                if (value < temp.data) {
                    parent = temp;
                    temp = temp.left;
                } else if (value > temp.data) {
                    parent = temp;
                    temp = temp.right;
                } else {
                    if (temp.left == null && temp.right == null) {
                        if (parent.left == temp) {
                            parent.left = null;
                        } else {
                            parent.right = null;
                        }
                    } else if (temp.left == null && temp.right != null) {
                        parent.right = temp.right;
                    } else if (temp.right == null && temp.left != null) {
                        parent.left = temp.left;
                    } else {
                        Node minNode = findMinInMaxTreeAndDelete(temp.right);
                        if (temp == this.root) {
                            this.root = minNode;
                        } else if (parent.left == temp) {
                            parent.left = minNode;
                        } else {
                            parent.right = minNode;
                        }
                        minNode.left = temp.left;
                        minNode.right = temp.right;
                    }
                    break;
                }
            }
        }
    }

    public Node findMinInMaxTreeAndDelete(Node node) {
        if (node == null) {
            throw new IllegalStateException("This is weird");
        }
        Node temp = node;
        Node parent = null;
        while (temp != null && temp.left != null) {
            parent = temp;
            temp = temp.left;
        }
        parent.left = null;
        return temp;
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
