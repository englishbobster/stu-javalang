package stos.experiments.javalang.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class BinaryTreeTest {

    private static BinaryTree TREE;
    private static final int DELETE_VALUE = 88;
    private static final int[] TEST_VALUES = new int[]{1, 4, 7, 2, 8, 10, 67, 3, 6, DELETE_VALUE, 33, 44};

    /*
     * Test tree
     *                                1
     *                                 \
     *                                  4
     *                                /   \
     *                               2     7
     *                                \   / \
     *                                 3 6   8
     *                                        \
     *                                         10
     *                                           \
     *                                            67
     *                                           / \
     *                                          33  88
     *                                            \
     *                                             44
     */

    private static final String IN_ORDER_STRING = "1 2 3 4 6 7 8 10 33 44 67 88 ";
    private static final String BREADTH_ORDER_STRING = "1 4 2 7 3 6 8 10 67 33 88 44 ";

    @BeforeEach
    void setUp() {
        TREE = new BinaryTree();
        for (int value : TEST_VALUES) {
            TREE.insert(value);
        }
    }

    @Test
    void check_that_a_new_binary_tree_has_a_new_node_when_inserting() {
        for (int value : TEST_VALUES) {
            assertThat(TREE.contains(value), is(true));
        }
    }

    @Test
    void check_that_a_new_binary_tree_does_not_have_a_value() {
        assertThat(TREE.contains(100), is(false));
    }

    @Test
    void check_that_an_entry_is_deleted_from_the_tree() {
        assertThat(TREE.contains(DELETE_VALUE), is(true));
        TREE.delete(DELETE_VALUE);
        assertThat(TREE.contains(DELETE_VALUE), is(false));
    }

    @Test
    void traverse_tree_in_order_depth_first() {
        assertThat(TREE.getInOrderDepthFirst(), is(IN_ORDER_STRING));
    }

    @Test
    void traverse_tree_in_breadth_order() {
        assertThat(TREE.getBreadthFirst(), is(BREADTH_ORDER_STRING));
    }

}