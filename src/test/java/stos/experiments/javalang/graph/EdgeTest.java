package stos.experiments.javalang.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stos.experiments.javalang.graph.testutils.EdgeCost;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static stos.experiments.javalang.graph.Directionality.BIDIR;
import static stos.experiments.javalang.graph.Directionality.UNIDIR;

class EdgeTest {

    private Vertex<Integer> a;
    private Vertex<Integer> z;
    private Edge<Integer, EdgeCost> edgeUni;
    private Edge<Integer, EdgeCost> edgeBi;


    @BeforeEach
    void setUp() {
        a = new Vertex<>("A", 1);
        z = new Vertex<>("Z", 3);
        edgeUni = new Edge<>(a, z, UNIDIR, new EdgeCost(10));
        edgeBi = new Edge<>(a, z, BIDIR, new EdgeCost(10));
    }

    @Test
    void edge_should_have_terminating_vertex_ends() {
        assertThat(edgeUni.getAEnd(), is(a));
        assertThat(edgeUni.getZEnd(), is(z));
    }

    @Test
    void edge_should_have_retrievable_value() {
        assertThat(edgeUni.getValue().edgeCost(), is(10));
    }

    @Test
    void edge_should_have_directionality() {
        assertThat(edgeUni.getDirectionality(), is(UNIDIR));
    }

    @Test
    void edge_starts_with() {
        assertThat(edgeUni.startsAt(a), is(true));
        assertThat(edgeUni.startsAt(z), is(false));
    }

    @Test
    void edge_ends_with() {
        assertThat(edgeUni.endsAt(z), is(true));
        assertThat(edgeUni.endsAt(a), is(false));
    }

    @Test
    void edge_is_Bi_and_connects_vertices() {
        assertTrue(edgeBi.isBiDirectionalAndConnectsVertices(z, a));
    }

    @Test
    void edge_is_not_Uni_and_connects_vertices() {
        assertFalse(edgeBi.isUniDirectionalAndConnectsVertices(z, a));
    }

    @Test
    void edge_is_Uni_and_connects_vertices() {
        assertTrue(edgeUni.isUniDirectionalAndConnectsVertices(a, z));
    }

    @Test
    void edge_is_not_Bi_and_connects_vertices() {
        assertFalse(edgeUni.isBiDirectionalAndConnectsVertices(a, z));
        assertFalse(edgeUni.isBiDirectionalAndConnectsVertices(z, a));
    }
}