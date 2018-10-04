package stos.experiments.javalang.binarytree;

class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    TreeNode(int value) {
        this.value = value;
        left = null;
        right = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
