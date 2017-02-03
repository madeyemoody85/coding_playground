package balanced_bst.avl;

/**
 * Created by nachiketlondhe on 2/1/17.
 */
public class AVLTree {
    public static AVLNode root;

    public boolean isTreeEmpty() {
        return root == null;
    }

    public void resetTree() {
        root = null;
    }

    public int height(AVLNode node) {
        return node == null ? -1 : node.height;
    }

    // This will be the recursive method to add the tree node
    public AVLNode insert(int data, AVLNode node) {
        if (node == null) {
            node = new AVLNode(data);
        }

        if (data < node.data) {

        } else if (data > node.data) {

        } else
            ;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }
}
