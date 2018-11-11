package stos.experiments.javalang.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {

    private Set<Vertex> vertices;
    private Set<Edge> edges;

    public Graph(Set<Vertex> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    List<Route> getRoutes(Vertex a, Vertex z) {
        return getRoutes(a, a, z, new Route());
    }

    private List<Route> getRoutes(Vertex previous, Vertex a, Vertex z, Route route) {
        route = new Route(route);
        route.addVertexToRoute(a);
        Optional<Edge> edgeOptional = resolveEdgeBetween(previous, a);
        if (edgeOptional.isPresent()) {
            if (!route.getEdgesInRoute().contains(edgeOptional.get())) {
                route.addEdgeToRoute(edgeOptional.get());
            }
        }
        if (a.equals(z)) {
            List<Route> routes = new ArrayList<>();
            routes.add(route);
            return routes;
        }
        if (!vertices.contains(a)) {
            return Collections.emptyList();
        }
        List<Route> routes = new ArrayList<>();
        for (Vertex next : getNextVertices(a)) {
            if (!route.getVerticesInRoute().contains(next)) {
                List<Route> newRoutes = getRoutes(a, next, z, route);
                routes.addAll(newRoutes);
            }
        }
        return routes;
    }

    private List<Vertex> getNextVertices(Vertex a) {
        return edges.stream()
                    .filter(edge -> edge.startsAt(a))
                    .map(edge -> edge.getZEnd())
                    .collect(Collectors.toList());
    }

    private Optional<Edge> resolveEdgeBetween(Vertex a, Vertex z) {
        return edges.stream().filter(edge -> edge.getAEnd().equals(a) && edge.getZEnd().equals(z)).findFirst();
    }
}
