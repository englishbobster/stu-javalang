package stos.experiments.javalang.Graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class VertexTest {

    /* Test graph, 6 vertices and 8 edges
    *                          +---+           +---+
    *      +-------------------> B +-----------> F +----------+
    *      |                   +-+-+           +-^-+          |
    *    +-+-+                   |               |            |
    *    | A |                   |               |            |
    *    +-+-+                   |   +---+       |            |
    *      |                     +---> D +-------+            |
    *      |                         +-+-+                    |
    *      |                           |                    +-v-+
    *      |                           +--------------------> E |
    *      |                                                +-^-+
    *      |                                                  |
    *      |                         +---+                    |
    *      +-------------------------> C +--------------------+
    *                                +---+
    */

    @Test
    void should_have_a_list_of_edges() {
        Vertex graphVertex = new Vertex();
        graphVertex.add(new Edge());
        List<Edge> edges = graphVertex.getEdges();
        assertThat(edges.size(), is(1));
    }

}