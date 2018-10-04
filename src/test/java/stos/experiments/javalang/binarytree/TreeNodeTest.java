package stos.experiments.javalang.binarytree;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class TreeNodeTest {

    @Test
    public void should_construct_a_single_tree_node_with_a_stored_value() {
        TreeNode node = new TreeNode(10);
        int storedValue = node.getValue();
        assertThat(storedValue, is(equalTo(10)));
    }

}