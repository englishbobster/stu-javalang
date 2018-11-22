package stos.experiments.javalang.graph;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class VertexTest {

    private static final Vertex<Integer> TEST_VERTEX = new Vertex<>("DONT PANIC", 1);

    @Test
    void should_have_a_label() {
        assertThat(TEST_VERTEX.getLabel(), is("DONT PANIC"));
    }

    @Test
    void should_return_correct_string() {
        assertThat(TEST_VERTEX.toString(), is("DONT PANIC : 1"));
    }
}