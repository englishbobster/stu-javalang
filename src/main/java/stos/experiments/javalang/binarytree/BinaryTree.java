package stos.experiments.javalang.binarytree;

class BinaryTree {

    TreeNode rootNode;

    BinaryTree() {
        this.rootNode = new TreeNode(0);
    }

    int getRootValue() {
        return rootNode.getValue();
    }

    void insert(int value) {
        rootNode.setValue(value);
    }
}
