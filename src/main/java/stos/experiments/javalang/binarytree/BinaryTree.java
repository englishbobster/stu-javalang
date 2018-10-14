package stos.experiments.javalang.binarytree;

import java.util.LinkedList;
import java.util.Queue;

class BinaryTree<T extends Comparable<T>> {

    private TreeNode<T> rootNode = null;

    BinaryTree() { }

    /**
     * - If the new node’s value is lower than the current node’s, we go to the left child
     * - If the new node’s value is greater than the current node’s, we go to the right child
     * - When the current node is null, we’ve reached a leaf node and we can insert the new node in that position
     */
    void insert(T value) {
        rootNode = insertRecursive(rootNode, value);
    }

    boolean contains(T value) {
        return containsNodeRecursive(rootNode, value);
    }

    void delete(T value) {
        rootNode = deleteRecursive(rootNode, value);
    }

    private TreeNode<T> insertRecursive(TreeNode<T> current, T value) {
        if (current == null) {
            current = new TreeNode<>(value);
        }
        if (value.compareTo(current.value) < 0) {
            current.left = insertRecursive(current.left, value);
        } else if (value.compareTo(current.value) > 0) {
            current.right = insertRecursive(current.right, value);
        }
        return current;
    }

    private boolean containsNodeRecursive(TreeNode<T> current, T value) {
        if (current == null) {
            return false;
        }
        if (current.value.compareTo(value) == 0) {
            return true;
        }
        if (current.value.compareTo(value) < 0) {
            return containsNodeRecursive(current.right, value);
        } else {
            return containsNodeRecursive(current.left, value);
        }
    }

    private TreeNode<T> deleteRecursive(TreeNode<T> current, T value) {
        if (current == null) {
            return null;
        }
        if (value.compareTo(current.value) == 0 ) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            T smallestValue = (T) findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value.compareTo(current.value) < 0) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private T findSmallestValue(TreeNode<T> current) {
        return current.left == null ? current.value : (T) findSmallestValue(current.left);
    }

    String getInOrderDepthFirst() {
        StringBuilder str = new StringBuilder();
        str = traverseInOrder(rootNode, str);
        return str.toString();
    }

    private StringBuilder traverseInOrder(TreeNode<T> node, StringBuilder stringBuilder) {
        if (node != null) {
            stringBuilder = traverseInOrder(node.left, stringBuilder);
            stringBuilder.append(node.value).append(" ");
            stringBuilder = traverseInOrder(node.right, stringBuilder);
        }
        return stringBuilder;
    }

    String getBreadthFirst() {
        StringBuilder str = new StringBuilder();
        if (rootNode == null) {
            return "";
        }
        Queue<TreeNode<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(rootNode);

        while (!nodeQueue.isEmpty()) {
            TreeNode removedNode = nodeQueue.remove();
            str.append(removedNode.value).append(" ");
            if (removedNode.left != null) {
                nodeQueue.add(removedNode.left);
            }
            if (removedNode.right != null) {
                nodeQueue.add(removedNode.right);
            }
        }
        return str.toString();
    }

    private class TreeNode<T extends Comparable<T>> {
        T value;
        TreeNode left;
        TreeNode right;

        TreeNode(T value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
}
