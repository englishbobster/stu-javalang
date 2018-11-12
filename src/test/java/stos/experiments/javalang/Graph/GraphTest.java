package stos.experiments.javalang.Graph;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {

    private static Vertex a;
    private static Vertex b;
    private static Vertex c;
    private static Vertex d;
    private static Vertex e;
    private static Vertex f;
    private static Vertex g;
    private static Vertex h;
    private static Edge ab;
    private static Edge ac;
    private static Edge be;
    private static Edge bd;
    private static Edge de;
    private static Edge df;
    private static Edge ef;
    private static Edge cf;
    private static Edge ch;
    private static Graph testGraph;

    /* Test graph (positively weighted), 8 vertices and 9 edges
     *                          +---+           +---+
     *      +-------------------> B +-----------> E +----------+
     *      |        10         +-+-+    15     +-^-+          |
     *    +-+-+                   |               |            |
     *    | A |                12 |               | 1          | 5            +---+
     *    +-+-+                   |   +---+       |            |              | G |
     *      |                     +---> D +-------+            |              +---+
     *      |                         +-+-+                    |
     *      | 15                        |                    +-v-+
     *      |                           +--------------------> F |
     *      |                                        2       +-^-+
     *      |                                                  |
     *      |                         +---+                    |
     *      +-------------------------> C +--------------------+
     *                                +---+          10
     *                                  |
     *                                  |                       +---+
     *                                  +-----------------------> H |
     *                                               4          +---+
     */

    @BeforeAll
    static void setUpClass() {
        Set<Vertex> vertices = new HashSet<>();
        a = new Vertex("A");
        vertices.add(a);
        b = new Vertex("B");
        vertices.add(b);
        c = new Vertex("C");
        vertices.add(c);
        d = new Vertex("D");
        vertices.add(d);
        e = new Vertex("E");
        vertices.add(e);
        f = new Vertex("F");
        vertices.add(f);
        g = new Vertex("G");
        vertices.add(g);
        h = new Vertex("H");

        Set<Edge> edges = new HashSet<>();
        ab = new Edge(a, b, 10);
        edges.add(ab);
        ac = new Edge(a, c, 15);
        edges.add(ac);
        be = new Edge(b, e, 15);
        edges.add(be);
        bd = new Edge(b, d, 12);
        edges.add(bd);
        de = new Edge(d, e, 1);
        edges.add(de);
        df = new Edge(d, f, 2);
        edges.add(df);
        ef = new Edge(e, f, 5);
        edges.add(ef);
        cf = new Edge(c, f, 10);
        edges.add(cf);
        ch = new Edge(c, h, 4);
        edges.add(ch);
        testGraph = new Graph(vertices, edges);
    }

    @Test
    void should_get_an_empty_route_between_a_g() {
        List<Route> routes = testGraph.getRoutes(a, g);
        assertTrue(routes.isEmpty());
    }

    @Test
    void should_get_an_empty_route_between_f_a_proving_that_the_graph_is_directed() {
        List<Route> routes = testGraph.getRoutes(f, a);
        assertTrue(routes.isEmpty());
    }

    @Test
    void should_get_a_simple_route_between_a_b() {
        Route expectedRoute = new Route(a, b);
        expectedRoute.addEdgeToRoute(ab);
        List<Route> routes = testGraph.getRoutes(a, b);
        assertThat(routes.size(), is(1));
        assertThat(routes.get(0), is(expectedRoute));
    }

    @Test
    void should_find_all_routes_in_the_test_graph() {
        List<Route> routes = testGraph.getRoutes(a, f);
        Route route1 = new Route(a, b, e, f);
        route1.addEdgesToRoute(ab, be, ef);
        Route route2 = new Route(a, b, d, f);
        route2.addEdgesToRoute(ab, bd, df);
        Route route3 = new Route(a, b, d, e, f);
        route3.addEdgesToRoute(ab, bd, de, ef);
        Route route4 = new Route(a, c, f);
        route4.addEdgesToRoute(ac, cf);
        assertThat(routes.size(), is(4));
        assertThat(routes, hasItems(route1, route2, route3, route4));
        assertThat(route1.getEdgesInRoute(), hasItems(ab, be, ef));
        assertThat(route2.getEdgesInRoute(), hasItems(ab, bd, df));
        assertThat(route3.getEdgesInRoute(), hasItems(ab, bd, de, ef));
        assertThat(route4.getEdgesInRoute(), hasItems(ac, cf));
    }

    @Test
    void should_find_the_cheapest_route() {
        Route route = testGraph.findCheapestRoute(a, f);
        Route expected = new Route(a, b, d, f);
        expected.addEdgesToRoute(ab, bd, df);
        assertThat(route, is(equalTo(expected)));
        assertThat(route.getCost(), is(24));
    }

    @Test
    void should_return_empty_route_when_finding_cheapest_for_vertices_with_no_path() {
        Route route = testGraph.findCheapestRoute(a, g);
        assertThat(route, is(equalTo(Route.emptyRoute())));
        assertThat(route.getCost(), is(0));
    }
}
