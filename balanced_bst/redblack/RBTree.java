//package balanced_bst.redblack;
//
///**
// * Red-black tree based on Coreman
// *
// * Created by nachiketlondhe on 2/1/17.
// */
//public class RBTree {
//
//    protected RBNode sentinel;
//    protected RBNode root;
//
//    public class RBNode {
//
//
//        public RBNode left, right; // Nodes children
//        public RBNode parent; // Nodes parent
//
//        private Integer value;
//
//        public boolean isBlack;
//
//        public RBNode(Integer value) {
//            left = null;
//            right = null;
//            parent = null;
//            isBlack = false;
//            this.value = value;
//        }
//
//        public int getValue() {
//            return value;
//        }
//
//        public void setValue(int value) {
//            this.value = value;
//        }
//
//        public boolean isBlack() {
//            return isBlack;
//        }
//
//        public boolean isRed() {
//            return !isBlack;
//        }
//
//        public void blacken() {
//            isBlack = true;
//        }
//
//        public void redden() {
//            isBlack = false;
//        }
//
//        /**
//         * Do a left rotation around this node
//         */
//        public void leftRotate() {
//            RBNode x = this;
//            RBNode y = x.right;
//            x.right = y.left;
//            if (y.left != sentinel) {
//                y.left.parent = x;
//            }
//            y.parent = x.parent;
//            if (x.parent == sentinel) {
//                root = y;
//            } else if (x == x.parent.left) {
//                x.parent.left = y;
//            } else {
//                x.parent.right = y;
//            }
//
//            y.left = x;
//            x.parent = y;
//        }
//
//        /**
//         * Do the right rotation around this node
//         */
//        public void rightRotate() {
//            RBNode y = this;
//            RBNode x = y.right;
//            y.left = x.right;
//            if (x.right != sentinel) {
//                x.right.parent = y;
//            }
//            x.parent = y.parent;
//            if (y.parent == sentinel) {
//                root = x;
//            } else if (y == y.parent.right) {
//                y.parent.right = x;
//            } else {
//                y.parent.left = x;
//            }
//
//            x.right = y;
//            y.parent = x;
//        }
//
//        /**
//         * At this point you should be able to construct the tree
//         */
//        public void rbInsertFixup() {
//            RBNode z = this;
//
//            while (z.parent.isRed()) {
//                // z's parent is a left child
//                if( z.parent == z.parent.parent.left) {
//                    RBNode y = z.parent.parent.right;
//
//                    // z's uncle is red
//                    if (y.isRed()) {
//                        // Make z's uncle and parent both black
//                        z.parent.blacken();
//                        y.blacken();
//                        // Make z's grandparent red
//                        z.parent.parent.redden();
//
//                        // Propogate z all the way up to the fixed node
//                        z = z.parent.parent;
//
//                    } else {
//                        // z's uncle y is black and z is a right child
//                        if ( z == z.parent.right) {
//                            z = z.parent;
//                            z.leftRotate();
//                        }
//                        // z's uncle y is black and z is a left child
//                        z.parent.blacken();
//                        z.parent.parent.redden();
//                        z.rightRotate();
//                    }
//
//                // z's parent is a right child.  Do the same as when z's
//                // parent is a left child, but exchange "left" and "right."
//                } else {
//                    RBNode y = z.parent.parent.left;
//
//                    // z's uncle is red
//                    if (y.isRed()) {
//                        // Make z's uncle and parent both black
//                        z.parent.blacken();
//                        y.blacken();
//                        // Make z's grand parent red
//                        z.parent.parent.redden();
//
//                        // Propogate z all the way up to node we just fixed
//                        z = z.parent.parent;
//                    } else {
//                        // z's uncle is black and z is a left child
//                        if (z == z.parent.left) {
//                            z = z.parent;
//                            z.rightRotate();
//                        }
//                        // z's uncle is black and z is a right child
//                        z.parent.blacken();
//                        z.parent.parent.redden();
//                        z.leftRotate();
//                    }
//                }
//            }
//
//            // the root of the tree is always black
//            z.blacken();
//        }
//    }
//
//    public RBTree() {
//        sentinel = new RBNode(null);
//        sentinel.left = sentinel;
//        sentinel.right = sentinel;
//        sentinel.parent = sentinel;
//        sentinel.blacken();
//        root = sentinel;
//    }
//
//    public RBNode insert(Integer value) {
//        RBNode z = new RBNode(value);
//
//        RBNode x = root;
//
//        RBNode xParent = sentinel;
//
//        while (x != sentinel ) {
//            xParent = x;
//            if (value < x.value) {
//                x = x.left;
//            } else {
//                x = x.right;
//            }
//        }
//
//        z.parent = xParent;
//
//        if (xParent == sentinel) { // This is the empty tree
//            root = z;              // Make z your new root
//        } else {
//            if ( value < xParent.value )
//                xParent.left = z;
//            else
//                xParent.right = z;
//        }
//
//        z.left = sentinel;
//        z.right = sentinel;
//        z.redden();
//        z.rbInsertFixup();
//        return z;
//    }
//
//    public static void main(String args[]) {
//        System.out.println("Starting red-black tree creation...");
//
//        RBTree tree = new RBTree();
//
//        long startTime = System.currentTimeMillis();
//
//        for ( int i = 1; i < 10 ; i ++ ) {
//            tree.insert(i);
//        }
//
//        System.out.println("1000 nodes inserted in : " + (System.currentTimeMillis() - startTime)/1000 + "ms");
////        tree.insert(13);
////        tree.insert(8);
////        tree.insert(17);
////        tree.insert(1);
////        tree.insert(15);
////        tree.insert(11);
////        tree.insert(25);
////        tree.insert(27);
////        tree.insert(22);
////        tree.insert(6);
//
//        System.out.println("Tree creation complete its unbalanced...");
//    }
//}
//
//
//package edu.lmu.cs.collections;
//
//        import java.awt.Color;
//        import java.util.Comparator;
//
///**
// * A simple red-black tree class.
// */
//public class BinarySearchTreeRedBlackTree extends BinarySearchTree {
//
//    /**
//     * Constructs an empty RedBlackTree that can only accept Comparables as
//     * items.
//     */
//    public RedBlackTree() {
//        this(null);
//    }
//
//    /**
//     * Constructs an empty RedBlackTree that orders its items according to the
//     * given comparator.
//     */
//    public RedBlackTree(Comparator c) {
//        super(c);
//    }
//
//    /**
//     * The nodes in a red-black tree store a color together with the actual data
//     * in the node.
//     */
//    class Node extends LinkedBinaryTreeNode {
//        Color color = Color.black;
//
//        public Node(Object data) {
//            super(data);
//        }
//    }
//
//    /**
//     * Adds a single data item to the tree. If there is already an item in the
//     * tree that compares equal to the item being inserted, it is "overwritten"
//     * by the new item. Overrides BinarySearchTree.add because the tree needs to
//     * be adjusted after insertion.
//     */
//    public void add(Object data) {
//        if (root == null) {
//            root = new Node(data);
//        }
//        BinaryTreeNode n = root;
//        while (true) {
//            int comparisonResult = compare(data, n.getData());
//            if (comparisonResult == 0) {
//                n.setData(data);
//                return;
//            } else if (comparisonResult < 0) {
//                if (n.getLeft() == null) {
//                    n.setLeft(new Node(data));
//                    adjustAfterInsertion((Node) n.getLeft());
//                    break;
//                }
//                n = n.getLeft();
//            } else { // comparisonResult > 0
//                if (n.getRight() == null) {
//                    n.setRight(new Node(data));
//                    adjustAfterInsertion((Node) n.getRight());
//                    break;
//                }
//                n = n.getRight();
//            }
//        }
//    }
//
//    /**
//     * Removes the node containing the given value. Does nothing if there is no
//     * such node.
//     */
//    public void remove(Object data) {
//        Node node = (Node) nodeContaining(data);
//        if (node == null) {
//            // No such object, do nothing.
//            return;
//        } else if (node.getLeft() != null && node.getRight() != null) {
//            // Node has two children, Copy predecessor data in.
//            BinaryTreeNode predecessor = predecessor(node);
//            node.setData(predecessor.getData());
//            node = (Node) predecessor;
//        }
//        // At this point node has zero or one child
//        Node pullUp = leftOf(node) == null ? rightOf(node) : leftOf(node);
//        if (pullUp != null) {
//            // Splice out node, and adjust if pullUp is a double black.
//            if (node == root) {
//                setRoot(pullUp);
//            } else if (node.getParent().getLeft() == node) {
//                node.getParent().setLeft(pullUp);
//            } else {
//                node.getParent().setRight(pullUp);
//            }
//            if (isBlack(node)) {
//                adjustAfterRemoval(pullUp);
//            }
//        } else if (node == root) {
//            // Nothing to pull up when deleting a root means we emptied the tree
//            setRoot(null);
//        } else {
//            // The node being deleted acts as a double black sentinel
//            if (isBlack(node)) {
//                adjustAfterRemoval(node);
//            }
//            node.removeFromParent();
//        }
//    }
//
//    /**
//     * Classic algorithm for fixing up a tree after inserting a node.
//     */
//    private void adjustAfterInsertion(Node n) {
//        // Step 1: color the node red
//        setColor(n, Color.red);
//
//        // Step 2: Correct double red problems, if they exist
//        if (n != null && n != root && isRed(parentOf(n))) {
//
//            // Step 2a (simplest): Recolor, and move up to see if more work
//            // needed
//            if (isRed(siblingOf(parentOf(n)))) {
//                setColor(parentOf(n), Color.black);
//                setColor(siblingOf(parentOf(n)), Color.black);
//                setColor(grandparentOf(n), Color.red);
//                adjustAfterInsertion(grandparentOf(n));
//            }
//
//            // Step 2b: Restructure for a parent who is the left child of the
//            // grandparent. This will require a single right rotation if n is
//            // also
//            // a left child, or a left-right rotation otherwise.
//            else if (parentOf(n) == leftOf(grandparentOf(n))) {
//                if (n == rightOf(parentOf(n))) {
//                    rotateLeft(n = parentOf(n));
//                }
//                setColor(parentOf(n), Color.black);
//                setColor(grandparentOf(n), Color.red);
//                rotateRight(grandparentOf(n));
//            }
//
//            // Step 2c: Restructure for a parent who is the right child of the
//            // grandparent. This will require a single left rotation if n is
//            // also
//            // a right child, or a right-left rotation otherwise.
//            else if (parentOf(n) == rightOf(grandparentOf(n))) {
//                if (n == leftOf(parentOf(n))) {
//                    rotateRight(n = parentOf(n));
//                }
//                setColor(parentOf(n), Color.black);
//                setColor(grandparentOf(n), Color.red);
//                rotateLeft(grandparentOf(n));
//            }
//        }
//
//        // Step 3: Color the root black
//        setColor((Node) root, Color.black);
//    }
//
//    /**
//     * Classic algorithm for fixing up a tree after removing a node; the
//     * parameter to this method is the node that was pulled up to where the
//     * removed node was.
//     */
//    private void adjustAfterRemoval(Node n) {
//        while (n != root && isBlack(n)) {
//            if (n == leftOf(parentOf(n))) {
//                // Pulled up node is a left child
//                Node sibling = rightOf(parentOf(n));
//                if (isRed(sibling)) {
//                    setColor(sibling, Color.black);
//                    setColor(parentOf(n), Color.red);
//                    rotateLeft(parentOf(n));
//                    sibling = rightOf(parentOf(n));
//                }
//                if (isBlack(leftOf(sibling)) && isBlack(rightOf(sibling))) {
//                    setColor(sibling, Color.red);
//                    n = parentOf(n);
//                } else {
//                    if (isBlack(rightOf(sibling))) {
//                        setColor(leftOf(sibling), Color.black);
//                        setColor(sibling, Color.red);
//                        rotateRight(sibling);
//                        sibling = rightOf(parentOf(n));
//                    }
//                    setColor(sibling, colorOf(parentOf(n)));
//                    setColor(parentOf(n), Color.black);
//                    setColor(rightOf(sibling), Color.black);
//                    rotateLeft(parentOf(n));
//                    n = (Node) root;
//                }
//            } else {
//                // pulled up node is a right child
//                Node sibling = leftOf(parentOf(n));
//                if (isRed(sibling)) {
//                    setColor(sibling, Color.black);
//                    setColor(parentOf(n), Color.red);
//                    rotateRight(parentOf(n));
//                    sibling = leftOf(parentOf(n));
//                }
//                if (isBlack(leftOf(sibling)) && isBlack(rightOf(sibling))) {
//                    setColor(sibling, Color.red);
//                    n = parentOf(n);
//                } else {
//                    if (isBlack(leftOf(sibling))) {
//                        setColor(rightOf(sibling), Color.black);
//                        setColor(sibling, Color.red);
//                        rotateLeft(sibling);
//                        sibling = leftOf(parentOf(n));
//                    }
//                    setColor(sibling, colorOf(parentOf(n)));
//                    setColor(parentOf(n), Color.black);
//                    setColor(leftOf(sibling), Color.black);
//                    rotateRight(parentOf(n));
//                    n = (Node) root;
//                }
//            }
//        }
//        setColor(n, Color.black);
//    }
//
//    // The following helpers dramatically simplify the code by getting
//    // all the null pointer checking out of the adjustment methods.
//
//    private Color colorOf(Node n) {
//        return n == null ? Color.black : n.color;
//    }
//
//    private boolean isRed(Node n) {
//        return n != null && colorOf(n) == Color.red;
//    }
//
//    private boolean isBlack(Node n) {
//        return n == null || colorOf(n) == Color.black;
//    }
//
//    private void setColor(Node n, Color c) {
//        if (n != null)
//            n.color = c;
//    }
//
//    private Node parentOf(Node n) {
//        return n == null ? null : (Node) n.getParent();
//    }
//
//    private Node grandparentOf(Node n) {
//        return (n == null || n.getParent() == null) ? null : (Node) n
//                .getParent().getParent();
//    }
//
//    private Node siblingOf(Node n) {
//        return (n == null || n.getParent() == null) ? null : (n == n
//                .getParent().getLeft()) ? (Node) n.getParent().getRight()
//                : (Node) n.getParent().getLeft();
//    }
//
//    private Node leftOf(Node n) {
//        return n == null ? null : (Node) n.getLeft();
//    }
//
//    private Node rightOf(Node n) {
//        return n == null ? null : (Node) n.getRight();
//    }
//}
//
//
