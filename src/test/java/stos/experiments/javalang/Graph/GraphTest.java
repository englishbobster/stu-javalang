package stos.experiments.javalang.Graph;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GraphTest {

    private static Vertex a;
    private static Vertex b;
    private static Vertex c;
    private static Vertex d;
    private static Vertex e;
    private static Vertex f;
    private static Vertex g;
    private static Edge ab;
    private static Edge ac;
    private static Edge be;
    private static Edge bd;
    private static Edge de;
    private static Edge df;
    private static Edge ef;
    private static Edge cf;
    private static Graph testGraph;

    /* Test graph (positively weighted), 6 vertices and 8 edges
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
        testGraph = new Graph(vertices, edges);
    }

    @Test
    void should_get_a_simple_route_between_a_b() {
        Route expectedRoute = new Route(a, b);
        List<Route> routes = testGraph.getRoutes(a, b);
        assertThat(routes.size(), is(1));
        assertThat(routes.get(0), is(expectedRoute));
    }

    @Test
    void should_find_all_routes_in_the_test_graph() {
        List<Route> routes = testGraph.getRoutes(a, f);
        Route route1 = new Route(a, b, e, f);
        Route route2 = new Route(a, b, d, f);
        Route route3 = new Route(a, b, d, e, f);
        Route route4 = new Route(a, c, f);
        assertThat(routes.size(), is(4));
        assertThat(routes, Matchers.hasItems(route1, route2, route3, route4));
    }
}
