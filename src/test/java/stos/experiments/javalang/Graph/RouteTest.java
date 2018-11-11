package stos.experiments.javalang.Graph;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RouteTest {

    @Test
    void should_be_able_to_iterate_over_vertices_in_route() {
        Route route = new Route(
                new Vertex("a"),
                new Vertex("b"),
                new Vertex("c"),
                new Vertex("d")
        );

        for (Vertex v : route) {
            assertThat(v.getLabel(), is(Matchers.isOneOf("a", "d", "c", "b")));
        }
    }

}