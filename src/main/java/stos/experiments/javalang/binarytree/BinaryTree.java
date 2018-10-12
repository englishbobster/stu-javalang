package stos.experiments.javalang.binarytree;

class BinaryTree {

    TreeNode rootNode = null;

    BinaryTree() { }

    /**
     * - If the new node’s value is lower than the current node’s, we go to the left child
     * - If the new node’s value is greater than the current node’s, we go to the right child
     * - When the current node is null, we’ve reached a leaf node and we can insert the new node in that position
     */
    void insert(int value) {
        rootNode = insertRecursive(rootNode, value);
    }

    boolean contains(int value) {
        return containsNodeRecursive(rootNode, value);
    }

    private TreeNode insertRecursive(TreeNode current, int value) {
        if (current == null) {
            current = new TreeNode(value);
        }
        if (value < current.value) {
            current.left = insertRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = insertRecursive(current.right, value);
        }
        return current;
    }

    private boolean containsNodeRecursive(TreeNode current, int value) {
        if (current == null) {
            return false;
        }
        if (current.value == value) {
            return true;
        }
        if (current.value < value) {
            return containsNodeRecursive(current.right, value);
        } else {
            return containsNodeRecursive(current.left, value);
        }
    }

    void delete(int value) {
        rootNode = deleteRecursive(rootNode, value);
    }

    private TreeNode deleteRecursive(TreeNode current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.value) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (current.value > value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(TreeNode current) {
        return current.left == null ? current.value :findSmallestValue(current.left);
    }

    private class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
}
