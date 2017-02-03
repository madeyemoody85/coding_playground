package balanced_bst.avl;

/**
 * Created by nachiketlondhe on 2/1/17.
 */
public class AVLNode {
    public AVLNode left, right;
    public int data;
    public int height;

    public AVLNode(int data) {
        this.left = null;
        this.right = null;
        this.data = data;
        this.height = 0;
    }
}
