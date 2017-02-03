package balanced_bst.redblack;

/**
 * Red-black tree based on Coreman
 *
 * Created by nachiketlondhe on 2/1/17.
 */
public class RBTree {

    protected RBNode sentinel;
    protected RBNode root;

    public class RBNode {


        public RBNode left, right; // Nodes children
        public RBNode parent; // Nodes parent

        private Integer value;

        public boolean isBlack;

        public RBNode(Integer value) {
            left = null;
            right = null;
            parent = null;
            isBlack = false;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public boolean isBlack() {
            return isBlack;
        }

        public boolean isRed() {
            return !isBlack;
        }

        public void blacken() {
            isBlack = true;
        }

        public void redden() {
            isBlack = false;
        }

        /**
         * Do a left rotation around this node
         */
        public void leftRotate() {
            RBNode x = this;
            RBNode y = x.right;
            x.right = y.left;
            if (y.left != sentinel) {
                y.left.parent = x;
            }
            y.parent = x.parent;
            if (x.parent == sentinel) {
                root = y;
            } else if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }

            y.left = x;
            x.parent = y;
        }

        /**
         * Do the right rotation around this node
         */
        public void rightRotate() {
            RBNode y = this;
            RBNode x = y.right;
            y.left = x.right;
            if (x.right != sentinel) {
                x.right.parent = y;
            }
            x.parent = y.parent;
            if (y.parent == sentinel) {
                root = x;
            } else if (y == y.parent.right) {
                y.parent.right = x;
            } else {
                y.parent.left = x;
            }

            x.right = y;
            y.parent = x;
        }

        /**
         * At this point you should be able to construct the tree
         */
        public void rbInsertFixup() {
            RBNode z = this;

            while (z.parent.isRed()) {
                // z's parent is a left child
                if( z.parent == z.parent.parent.left) {
                    RBNode y = z.parent.parent.right;

                    // z's uncle is red
                    if (y.isRed()) {
                        // Make z's uncle and parent both black
                        z.parent.blacken();
                        y.blacken();
                        // Make z's grandparent red
                        z.parent.parent.redden();

                        // Propogate z all the way up to the fixed node
                        z = z.parent.parent;

                    } else {
                        // z's uncle y is black and z is a right child
                        if ( z == z.parent.right) {
                            z = z.parent;
                            z.leftRotate();
                        }
                        // z's uncle y is black and z is a left child
                        z.parent.blacken();
                        z.parent.parent.redden();
                        z.rightRotate();
                    }

                // z's parent is a right child.  Do the same as when z's
                // parent is a left child, but exchange "left" and "right."
                } else {
                    RBNode y = z.parent.parent.left;

                    // z's uncle is red
                    if (y.isRed()) {
                        // Make z's uncle and parent both black
                        z.parent.blacken();
                        y.blacken();
                        // Make z's grand parent red
                        z.parent.parent.redden();

                        // Propogate z all the way up to node we just fixed
                        z = z.parent.parent;
                    } else {
                        // z's uncle is black and z is a left child
                        if (z == z.parent.left) {
                            z = z.parent;
                            z.rightRotate();
                        }
                        // z's uncle is black and z is a right child
                        z.parent.blacken();
                        z.parent.parent.redden();
                        z.leftRotate();
                    }
                }
            }

            // the root of the tree is always black
            z.blacken();
        }
    }

    public RBTree() {
        sentinel = new RBNode(null);
        sentinel.left = sentinel;
        sentinel.right = sentinel;
        sentinel.parent = sentinel;
        sentinel.blacken();
        root = sentinel;
    }

    public RBNode insert(Integer value) {
        RBNode z = new RBNode(value);

        RBNode x = root;

        RBNode xParent = sentinel;

        while (x != sentinel ) {
            xParent = x;
            if (value < x.value) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        z.parent = xParent;

        if (xParent == sentinel) { // This is the empty tree
            root = z;              // Make z your new root
        } else {
            if ( value < xParent.value )
                xParent.left = z;
            else
                xParent.right = z;
        }

        z.left = sentinel;
        z.right = sentinel;
        z.redden();
        z.rbInsertFixup();
        return z;
    }

    public static void main(String args[]) {
        System.out.println("Starting red-black tree creation...");

        RBTree tree = new RBTree();

        long startTime = System.currentTimeMillis();

        for ( int i = 1; i < 10 ; i ++ ) {
            tree.insert(i);
        }

        System.out.println("1000 nodes inserted in : " + (System.currentTimeMillis() - startTime)/1000 + "ms");
//        tree.insert(13);
//        tree.insert(8);
//        tree.insert(17);
//        tree.insert(1);
//        tree.insert(15);
//        tree.insert(11);
//        tree.insert(25);
//        tree.insert(27);
//        tree.insert(22);
//        tree.insert(6);

        System.out.println("Tree creation complete its unbalanced...");
    }
}
