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
class Route<T, U extends hasEdgeCost> implements Iterable<Vertex<T>>{
    private List<Vertex<T>> verticesInRoute;
    private List<Edge<U>> edgesInRoute;

    static Route emptyRoute() {
        return new Route();
    }

    private Route() {
        verticesInRoute = new ArrayList<>();
        edgesInRoute = new ArrayList<>();
    }

    @SafeVarargs
    Route(Vertex<T>... vertex) {
        this();
        verticesInRoute.addAll(Arrays.asList(vertex));
    }

    Route(Route<T, U> route) {
        this();
        verticesInRoute.addAll(route.verticesInRoute);
        edgesInRoute.addAll(route.edgesInRoute);
    }

    void addVertexToRoute(Vertex<T> vertex) {
        verticesInRoute.add(vertex);
    }

    void addEdgeToRoute(Edge<U> edge) {
        edgesInRoute.add(edge);
    }

    @SafeVarargs
    final void addEdgesToRoute(Edge<U>... edges) {
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
    public Iterator<Vertex<T>> iterator() {
        return verticesInRoute.iterator();
    }
}
