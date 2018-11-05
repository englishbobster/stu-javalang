package stos.experiments.javalang.Graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@Getter
class Route {
    List<Vertex> verticesInRoute;

    Route() {
        verticesInRoute = new ArrayList<>();
    }

    Route(Vertex vertex) {
        verticesInRoute = new ArrayList<>();
        verticesInRoute.add(vertex);
    }

    Route(Vertex... vertex ) {
        verticesInRoute = new ArrayList<>();
        verticesInRoute.addAll(Arrays.asList(vertex));
    }

    Route(Route route) {
        verticesInRoute = new ArrayList<>();
        verticesInRoute.addAll(route.verticesInRoute);
    }

    void addToRoute(Vertex vertex) {
        verticesInRoute.add(vertex);
    }

    @Override
    public String toString() {
        return verticesInRoute.stream().map(vertex -> vertex.toString()).collect(Collectors.joining("::"));
    }

}
