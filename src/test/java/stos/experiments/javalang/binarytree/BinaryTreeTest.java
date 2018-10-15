package stos.experiments.javalang.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class BinaryTreeTest {

    public static final int ABSENT_INTEGER = 100;
    public static final String ABSENT_WORD = "Hippopotamus";
    private BinaryTree TREE;
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

    private static final String IN_ORDER_STRING = "1 2 3 4 6 7 8 10 33 44 67 88";
    private static final String IN_ORDER_STRING_WORD = "Stu Help Elevator Kebab Mongolian Necessary Traverse Solarian";
    private static final String BREADTH_ORDER_STRING = "1 4 2 7 3 6 8 10 67 33 88 44";
    private static final String BREADTH_ORDER_STRING_WORD = "Stu Mongolian Elevator Traverse Help Kebab Necessary Solarian";

    private BinaryTree<Word> wordTree;
    private Word DELETE_GENERIC_VALUE = new Word("Elevator");
    private Word[] words = new Word[]{new Word("Stu"), new Word("Mongolian"),
            new Word("Traverse"), DELETE_GENERIC_VALUE, new Word("Kebab"),
            new Word("Solarian"), new Word("Necessary"), new Word("Help")};


    @BeforeEach
    void setUp() {
        TREE = new BinaryTree();
        for (int value : TEST_VALUES) {
            TREE.insert(value);
        }

        wordTree = new BinaryTree<>();
        for (Word word : words) {
            wordTree.insert(word);
        }
    }

    @Test
    void check_that_we_can_insert_values_in_a_tree() {
        for (int value : TEST_VALUES) {
            assertThat(TREE.contains(value), is(true));
        }
    }

    @Test
    void check_that_we_can_insert_generic_values_in_a_tree() {
        for (Word word : words) {
            assertThat(wordTree.contains(word), is(true));
        }
    }

    @Test
    void check_that_a_non_existant_value_is_not_found() {
        assertThat(TREE.contains(ABSENT_INTEGER), is(false));
    }

    @Test
    void check_that_a_non_existant_generic_value_is_not_found() {
        assertThat(wordTree.contains(new Word(ABSENT_WORD)), is(false));
    }

    @Test
    void check_that_an_entry_is_deleted_from_the_tree() {
        assertThat(TREE.contains(DELETE_VALUE), is(true));
        TREE.delete(DELETE_VALUE);
        assertThat(TREE.contains(DELETE_VALUE), is(false));
    }

    @Test
    void check_that_a_generic_entry_is_deleted_from_the_tree() {
        assertThat(wordTree.contains(DELETE_GENERIC_VALUE), is(true));
        wordTree.delete(DELETE_GENERIC_VALUE);
        assertThat(wordTree.contains(DELETE_GENERIC_VALUE), is(false));
    }

    @Test
    void traverse_tree_in_order_depth_first() {
        List<Integer> inOrderDepthFirst = TREE.getInOrderDepthFirst();
        String str = inOrderDepthFirst.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(" "));
        assertThat(str, is(IN_ORDER_STRING));
    }

    @Test
    void traverse_tree_in_order_depth_first_generic() {
        List<Word> inOrderDepthFirst = wordTree.getInOrderDepthFirst();
        String str = inOrderDepthFirst.stream().map(word -> word.toString()).collect(Collectors.joining(" "));
        assertThat(str, is(IN_ORDER_STRING_WORD));
    }

    @Test
    void traverse_tree_in_breadth_order() {
        List<Integer> breadthFirst = TREE.getBreadthFirst();
        String str = breadthFirst.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(" "));
        assertThat(str, is(BREADTH_ORDER_STRING));
    }

    @Test
    void traverse_tree_in_breadth_order_generic() {
        List<Word> breadthFirst = wordTree.getBreadthFirst();
        String str = breadthFirst.stream().map(word -> word.toString()).collect(Collectors.joining(" "));
        assertThat(str, is(BREADTH_ORDER_STRING_WORD));
    }

    public class Word implements Comparable<Word> {
        String theWord;

        public Word(String theWord) {
            this.theWord = theWord;
        }

        /**
         * A simple way to implement compareTo...madness!
         * Strings already have a compareTo that compares lexicographically
         */
        @Override
        public int compareTo(Word o) {
            int thisHash = theWord.hashCode();
            int thatHash = o.theWord.hashCode();
            return thisHash - thatHash;
        }

        @Override
        public String toString() {
            return theWord;
        }
    }

}