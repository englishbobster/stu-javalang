package stos.experiments.javalang.Graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static stos.experiments.javalang.Graph.Directionality.*;

class EdgeTest {

    private Vertex a;
    private Vertex z;
    private Edge edge;

    @BeforeEach
    void setUp() {
        a = new Vertex("A");
        z = new Vertex("Z");
        edge = new Edge(a, z, UNIDIR,10);
    }

    @Test
    void edge_should_have_terminating_vertex_ends() {
        assertThat(edge.getAEnd(), is(a));
        assertThat(edge.getZEnd(), is(z));
    }

    @Test
    void edge_should_have_retrievable_value() {
         assertThat(edge.getValue(), is(10));
    }

    @Test
    void edge_should_have_directionality() {
        assertThat(edge.getDirectionality(), is(UNIDIR));
    }

    @Test
    void edge_starts_with() {
        assertThat(edge.startsAt(a), is(true));
        assertThat(edge.startsAt(z), is(false));
    }

    @Test
    void edge_ends_with() {
        assertThat(edge.endsAt(z), is(true));
        assertThat(edge.endsAt(a), is(false));
    }
}