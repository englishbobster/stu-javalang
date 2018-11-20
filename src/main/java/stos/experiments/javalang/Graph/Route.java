package stos.experiments.javalang.Graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@Getter
class Route implements Iterable<Vertex>{
    private List<Vertex> verticesInRoute;
    private List<Edge> edgesInRoute;

    static Route emptyRoute() {
        return new Route();
    }

    private Route() {
        verticesInRoute = new ArrayList<>();
        edgesInRoute = new ArrayList<>();
    }

    Route(Vertex... vertex ) {
        this();
        verticesInRoute.addAll(Arrays.asList(vertex));
    }

    Route(Route route) {
        this();
        verticesInRoute.addAll(route.verticesInRoute);
        edgesInRoute.addAll(route.edgesInRoute);
    }

    void addVertexToRoute(Vertex vertex) {
        verticesInRoute.add(vertex);
    }

    void addEdgeToRoute(Edge edge) {
        edgesInRoute.add(edge);
    }

    void addEdgesToRoute(Edge... edges) {
        edgesInRoute.addAll(Arrays.asList(edges));
    }

    int getCost() {
        return edgesInRoute.stream().map(Edge::getValue).reduce(Integer::sum).orElse(0);
    }

    @Override
    public String toString() {
        return verticesInRoute.stream().map(Vertex::toString).collect(Collectors.joining("::"));
    }

    @Override
    public Iterator<Vertex> iterator() {
        return verticesInRoute.iterator();
    }
}
