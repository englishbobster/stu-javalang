package stos.experiments.javalang.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class Graph {

    private Set<Vertex> vertices;
    private Set<Edge> edges;

    Graph(Set<Vertex> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    /**
     * Gets all possible routes between two vertices(nodes).
     *
     * @param a The start vertex.
     * @param z The end vertex.
     * @return A list of Route objects @link Route#Route() which contain the corresponding lists of vertices and edges.
     */
    List<Route> getRoutes(Vertex a, Vertex z) {
        return getRoutes(a, a, z, Route.emptyRoute());
    }

    /**
     * Find the cheapest route between two vertices(nodes).
     *
     * @param a The start vertex.
     * @param z The end vertex.
     * @return The route with the cheapest cost.
     */
    Route findCheapestRoute(Vertex a, Vertex z) {
        Map<Route, Integer> route2Cost = getRoutes(a, z).stream()
                .collect(Collectors.toMap(Function.identity(), Route::getCost));
        Optional<Map.Entry<Route, Integer>> minRouteEntry = route2Cost
                .entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue));
        if (minRouteEntry.isPresent()) {
            return minRouteEntry.get().getKey();
        }
        return Route.emptyRoute();
    }

    private List<Route> getRoutes(Vertex previous, Vertex a, Vertex z, Route route) {
        route = new Route(route);
        Optional<Edge> edgeOptional = resolveEdgeBetweenVertexes(previous, a);
        if (edgeOptional.isPresent()) {
            if (!route.getEdgesInRoute().contains(edgeOptional.get())) {
                route.addEdgeToRoute(edgeOptional.get());
            }
        }
        route.addVertexToRoute(a);
        if (a.equals(z)) {
            List<Route> routes = new ArrayList<>();
            routes.add(route);
            return routes;
        }
        if (!vertices.contains(a)) {
            return Collections.emptyList();
        }
        List<Route> routes = new ArrayList<>();
        for (Vertex next : getNextVerticesConnectToVertex(a)) {
            if (!route.getVerticesInRoute().contains(next)) {
                List<Route> newRoutes = getRoutes(a, next, z, route);
                routes.addAll(newRoutes);
            }
        }
        return routes;
    }

    private Set<Vertex> getNextVerticesConnectToVertex(Vertex a) {
        Set<Vertex> result = new HashSet<>();
        result.addAll(getAllUniDirectionalEdgesAtVertex(a));
        result.addAll(getAllBiDirectionalEdgesAtVertex(a));
        return result;
    }

    private List<Vertex> getAllBiDirectionalEdgesAtVertex(Vertex a) {
        return edges.stream()
                    .filter(edge -> (edge.isBi() && edge.endsAt(a)))
                    .map(Edge::getAEnd)
                    .collect(Collectors.toList());
    }

    private List<Vertex> getAllUniDirectionalEdgesAtVertex(Vertex a) {
        return edges.stream()
                    .filter(edge -> edge.startsAt(a))
                    .map(Edge::getZEnd)
                    .collect(Collectors.toList());
    }

    private Optional<Edge> resolveEdgeBetweenVertexes(Vertex a, Vertex z) {
        return edges.stream()
                .filter(edge -> IsUniDirectionalAndConnectsVertices(a, z, edge)
                        || isBiDirectionalAndConnectsVertices(a, z, edge))
                .findFirst();
    }

    private boolean isBiDirectionalAndConnectsVertices(Vertex a, Vertex z, Edge edge) {
        return edge.isBi() && edge.getAEnd().equals(z) && edge.getZEnd().equals(a);
    }

    private boolean IsUniDirectionalAndConnectsVertices(Vertex a, Vertex z, Edge edge) {
        return edge.getAEnd().equals(a) && edge.getZEnd().equals(z);
    }
}
