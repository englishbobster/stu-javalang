package stos.experiments.javalang.Graph;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class VertexTest {
    @Test
    void should_have_a_label() {
        Vertex v = new Vertex("DONT PANIC");
        assertThat(v.getLabel(), is("DONT PANIC"));
    }
}