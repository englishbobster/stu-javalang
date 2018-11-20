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
import static stos.experiments.javalang.Graph.Directionality.*;

class GraphTest {

    private static Vertex<Integer> a;
    private static Vertex<Integer> b;
    private static Vertex<Integer> c;
    private static Vertex<Integer> d;
    private static Vertex<Integer> e;
    private static Vertex<Integer> f;
    private static Vertex<Integer> g;
    private static Vertex<Integer> h;
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

    /* Test graph (positively weighted), 8 vertices and 9 edges, bidirectional edges allowed
     *                          +---+           +---+
     *      +-------------------> B +-----------> E +----------+
     *      |        10         +-+-+    15     +-^-+          |
     *    +-+-+                   |               |            |
     *    | A |                12 |               | 1          | 5            +---+
     *    +-+-+                   |   +---+       |            |              | G |
     *      |                     +---> D <-------+            |              +---+
     *      |                         +---+                    |
     *      | 15                        |                    +-v-+
     *      |                           +--------------------> F |
     *      |                                        2       +-^-+
     *      |                                                  |
     *      |                         +---+                    |
     *      +-------------------------> C +--------------------+
     *                                +-^-+          10
     *                                  |
     *                                  |                       +---+
     *                                  +-----------------------> H |
     *                                               4          +---+
     */

    // Another dataset to consider:
    // London Underground geographic maps/CSV
    // https://commons.wikimedia.org/wiki/London_Underground_geographic_maps/CSV

    @BeforeAll
    static void setUpClass() {
        Set<Vertex> vertices = new HashSet<>();
        a = new Vertex<>("A", 1);
        vertices.add(a);
        b = new Vertex<>("B", 2);
        vertices.add(b);
        c = new Vertex<>("C", 3);
        vertices.add(c);
        d = new Vertex<>("D", 4);
        vertices.add(d);
        e = new Vertex<>("E", 5);
        vertices.add(e);
        f = new Vertex<>("F", 6);
        vertices.add(f);
        g = new Vertex<>("G", 7);
        vertices.add(g);
        h = new Vertex<>("H",8 );
        vertices.add(h);
        Set<Edge> edges = new HashSet<>();
        ab = new Edge(a, b, UNIDIR,10);
        edges.add(ab);
        ac = new Edge(a, c, UNIDIR,15);
        edges.add(ac);
        be = new Edge(b, e, UNIDIR,15);
        edges.add(be);
        bd = new Edge(b, d, UNIDIR,12);
        edges.add(bd);
        de = new Edge(d, e, BIDIR,1);
        edges.add(de);
        df = new Edge(d, f, UNIDIR,2);
        edges.add(df);
        ef = new Edge(e, f, UNIDIR,5);
        edges.add(ef);
        cf = new Edge(c, f, UNIDIR,10);
        edges.add(cf);
        ch = new Edge(c, h, BIDIR,4);
        edges.add(ch);
        testGraph = new Graph(vertices, edges);
    }

    @Test
    void should_get_an_empty_route_between_a_g() {
        List<Route> routes = testGraph.getRoutes(a, g);
        assertTrue(routes.isEmpty());
    }

    @Test
    void should_get_an_empty_route_between_f_a() {
        List<Route> routes = testGraph.getRoutes(f, a);
        assertTrue(routes.isEmpty());
    }

    @Test
    void should_get_only_one_route_a_h () {
        List<Route> routes = testGraph.getRoutes(a, h);
        assertThat(routes.size(), is(1));
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
        Route route5 = new Route(a, b, e, d, f);
        route5.addEdgesToRoute(ab, be, de, df);
        assertThat(routes.size(), is(5));
        assertThat(routes, hasItems(route1, route2, route3, route4, route5));
        assertThat(route1.getEdgesInRoute(), hasItems(ab, be, ef));
        assertThat(route2.getEdgesInRoute(), hasItems(ab, bd, df));
        assertThat(route3.getEdgesInRoute(), hasItems(ab, bd, de, ef));
        assertThat(route4.getEdgesInRoute(), hasItems(ac, cf));
        assertThat(route5.getEdgesInRoute(), hasItems(ab, be, de, df));
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
