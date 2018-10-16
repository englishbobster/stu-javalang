package stos.experiments.javalang.Graph;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class EdgeTest {

    @Test
    void edge_should_have_a_terminating_vertex_end() {
        Vertex z = new Vertex();
        Edge edge = new Edge(z, 10);
        assertThat(edge.getZEnd(), is(z));
    }

    @Test
    void edge_should_have_retrievable_value() {
        Vertex z = new Vertex();
        Edge edge = new Edge(z, 10);
        assertThat(edge.getValue(), is(10));
    }
}