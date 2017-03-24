package leetCode;

/**
 * Created by nachiketlondhe on 2/15/17.
 */
public class DeleteNode {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int i) {
            this.val = i;
            this.left = null;
            this.right = null;
        }
    }

    public TreeNode deleteNode(TreeNode root, int  val) {
        if (root == null) return null;

        TreeNode parent = null;
        TreeNode temp = root;

        boolean isLeft = false;

        // Find the node
        while (temp != null) {
            if (val == temp.val) {
                break;
            } else if ( val < temp.val) {
                isLeft = true;
                parent = temp;
                temp = temp.left;
            } else {
                isLeft = false;
                parent = temp;
                temp = temp.right;
            }
        }

        // In case we break of from while because of that

        if (temp == null) {
            return root;
        }

        if (temp.left == null && temp.right == null) {
            if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }  else if (temp.left == null || temp.right == null) {
            TreeNode child = null;

            if (temp.left != null) {
                child = temp.left;
            } else if (temp.right != null) {
                child = temp.right;
            }

            if (isLeft) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            TreeNode parent_of_replacement = null;

            // Go and find the max child in left tree
            TreeNode replacement = temp.left;
            isLeft = true;

            while (replacement.right != null) {
                parent_of_replacement = replacement;
                replacement = replacement.right;
                isLeft = false;
            }

            // Copy the value of replacement
            temp.val = replacement.val;

            if (isLeft) {
                temp.left = replacement.left;
            } else {
                parent_of_replacement.right = replacement.left;
            }
        }

        return root;
    }

    public TreeNode deleteNode2(TreeNode root, int val) {
        if (root == null) return null;

        TreeNode parent = null;
        TreeNode temp = root;
        TreeNode child;

        boolean is_left = true;

        // find the node
        while (temp != null){
            if (val == temp.val) {
                break;
            } else if (val < temp.val) {
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
            TreeNode parent_repl = temp;
            TreeNode replacement = temp.left;

            is_left = true;
            while (replacement.right != null) {
                parent_repl = replacement;
                replacement = replacement.right;
                is_left = false;
            }

            temp.val = replacement.val;

            if (is_left) {
                temp.left = replacement.left;
            } else {
                parent_repl.right = replacement.left;
            }
        }

        return root;
    }
}
