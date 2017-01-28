package com.google.interview.linkedlist;

/**
 * Created by nachiketlondhe on 1/10/17.
 */
public class BinarySearchTree {
    // root node of the tree
    private static TreeNode root;

    //      6
    //  4       8
    //1     5 7     9
    public static void main(String args[]) {

        BinarySearchTree tree = new BinarySearchTree();

        tree.addNode(6);
        tree.addNode(4);
        tree.addNode(8);
        tree.addNode(1);
        tree.addNode(5);
        tree.addNode(7);
        tree.addNode(9);

        System.out.println("Nodes added");
    }

    public void addNode(int value) {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            TreeNode temp = root;
            TreeNode parent_node = null;

            // traverse till you find the position
            while (temp != null) {
                if (temp.getValue() > value) {
                    parent_node = temp;
                    temp = temp.getLeft();
                } else {
                    parent_node = temp;
                    temp = temp.getRight();
                }
            }

            // At this point temp is null
            // create a new node to it
            temp = new TreeNode(value);
            if (parent_node.getValue() < value) {
                parent_node.right = temp;
            } else {
                parent_node.left = temp;
            }
        }
    }
}
