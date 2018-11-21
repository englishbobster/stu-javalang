package stos.experiments.javalang.graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@Getter
class Route<V, E extends hasEdgeCost> implements Iterable<Vertex<V>> {
    private List<Vertex<V>> verticesInRoute;
    private List<Edge<V, E>> edgesInRoute;

    static <V,E extends hasEdgeCost> Route<V, E> emptyRoute() {
        return new Route<>();
    }

    private Route() {
        verticesInRoute = new ArrayList<>();
        edgesInRoute = new ArrayList<>();
    }

    @SafeVarargs
    Route(Vertex<V>... vertex) {
        this();
        verticesInRoute.addAll(Arrays.asList(vertex));
    }

    Route(Route<V, E> route) {
        this();
        verticesInRoute.addAll(route.verticesInRoute);
        edgesInRoute.addAll(route.edgesInRoute);
    }

    void addVertexToRoute(Vertex<V> vertex) {
        verticesInRoute.add(vertex);
    }

    void addEdgeToRoute(Edge<V, E> edge) {
        edgesInRoute.add(edge);
    }

    @SafeVarargs
    final void addEdgesToRoute(Edge<V, E>... edges) {
        edgesInRoute.addAll(Arrays.asList(edges));
    }

    int getCost() {
        return edgesInRoute.stream().map(Edge::getValue)
                .map(hasEdgeCost::costOfEdge)
                .reduce(Integer::sum).orElse(0);
    }

    @Override
    public String toString() {
        return verticesInRoute.stream().map(Vertex::toString).collect(Collectors.joining("::"));
    }

    @Override
    public Iterator<Vertex<V>> iterator() {
        return verticesInRoute.iterator();
    }
}
