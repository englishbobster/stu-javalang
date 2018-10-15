package stos.experiments.javalang.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BinaryTree<T extends Comparable<T>> {

    private TreeNode<T> rootNode = null;

    BinaryTree() { }

    /**
     * - If the new node’s value is lower (comparable < 0) than the current node’s, we go to the left child
     * - If the new node’s value is greater (comparable > 0) than the current node’s, we go to the right child
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
            T smallestValue = findSmallestValue(current.right);
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
        return current.left == null ? current.value : findSmallestValue(current.left);
    }

    List<T> getInOrderDepthFirst() {
        List<T> order = new ArrayList<>();
        order = traverseInOrder(rootNode, order);
        return order;
    }

    private List<T> traverseInOrder(TreeNode<T> node, List<T> order) {
        if (node != null) {
            order = traverseInOrder(node.left, order);
            order.add(node.value);
            order = traverseInOrder(node.right, order);
        }
        return order;
    }

    List<T> getBreadthFirst() {
        Queue<TreeNode<T>> nodeQueue = new LinkedList<>();
        List<T> order = new ArrayList<>();
        if (rootNode == null) {
            return Collections.emptyList();
        }
        nodeQueue.add(rootNode);

        while (!nodeQueue.isEmpty()) {
            TreeNode<T> removedNode = nodeQueue.remove();
            order.add(removedNode.value);
            if (removedNode.left != null) {
                nodeQueue.add(removedNode.left);
            }
            if (removedNode.right != null) {
                nodeQueue.add(removedNode.right);
            }
        }
        return order;
    }

    /**
     * Anything we use for a value better implement Comparable interface.
     */
    private class TreeNode<U extends Comparable<T>> {
        U value;
        TreeNode<U> left;
        TreeNode<U> right;

        TreeNode(U value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
}
