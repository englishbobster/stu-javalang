package stos.experiments.javalang.Graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class VertexTest {

    @Test
    void should_have_a_list_of_edges() {
        Vertex nodeA = new Vertex();
        Vertex nodeB = new Vertex();
        nodeA.add(new Edge(nodeB, 10));
        List<Edge> edges = nodeA.getEdges();
        assertThat(edges.size(), is(1));
    }
}