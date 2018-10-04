package stos.experiments.javalang.binarytree;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class BinaryTreeTest {

    @Test
    public void check_that_a_new_binary_tree_has_a_root_node() {
        BinaryTree tree = new BinaryTree();
        assertThat(tree.getRootValue(), is(equalTo(0)));
    }

    @Test
    public void check_that_we_can_insert_value_into_root_node() {
        BinaryTree tree = new BinaryTree();
        tree.insert(10);
        assertThat(tree.getRootValue(), is(equalTo(10)));
    }

}