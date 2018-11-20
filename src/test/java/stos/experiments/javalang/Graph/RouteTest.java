package stos.experiments.javalang.Graph;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static stos.experiments.javalang.Graph.Directionality.UNIDIR;

class RouteTest {

    private Route route;

    @BeforeEach
    void setUp() {
        Vertex<Integer> a = new Vertex<>("a", 1);
        Vertex<Integer> b = new Vertex<>("b", 2);
        Vertex<Integer> c = new Vertex<>("c", 3);
        Vertex<Integer> d = new Vertex<>("d", 4);
        route = new Route(a, b, c, d);
        Edge ab = new Edge(a, b, UNIDIR, 5);
        Edge bc = new Edge(b, c, UNIDIR, 5);
        Edge cd = new Edge(c, d, UNIDIR, 5);
        route.addEdgesToRoute(ab, bc, cd);
    }

    @Test
    void should_be_able_to_iterate_over_vertices_in_route() {
        for (Vertex v : route) {
            assertThat(v.getLabel(), is(Matchers.isOneOf("a", "d", "c", "b")));
        }
    }

    @Test
    void should_calculate_cost_of_a_route() {
        assertThat(route.getCost(), is(15));
    }
}