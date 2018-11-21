package stos.experiments.javalang.graph;

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

class Graph<V, E extends CostableEdge> {

    private Set<Vertex<V>> vertices;
    private Set<Edge<V, E>> edges;

    Graph(Set<Vertex<V>> vertices, Set<Edge<V, E>> edges) {
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
    List<Route<V, E>> getRoutes(Vertex<V> a, Vertex<V> z) {
        return getRoutes(a, a, z, Route.emptyRoute());
    }

    /**
     * Find the cheapest route between two vertices(nodes).
     *
     * @param a The start vertex.
     * @param z The end vertex.
     * @return The route with the cheapest cost.
     */
    Route<V, E> findCheapestRoute(Vertex<V> a, Vertex<V> z) {
        Map<Route<V, E>, Integer> route2Cost = getRoutes(a, z).stream()
                .collect(Collectors.toMap(Function.identity(), Route::getCost));
        Optional<Map.Entry<Route<V, E>, Integer>> minRouteEntry = route2Cost
                .entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue));
        if (minRouteEntry.isPresent()) {
            return minRouteEntry.get().getKey();
        }
        return Route.emptyRoute();
    }

    private List<Route<V, E>> getRoutes(Vertex<V> previous, Vertex<V> a, Vertex<V> z, Route<V, E> route) {
        route = new Route<>(route);
        Optional<Edge<V, E>> edgeOptional = resolveEdgeBetweenVertexes(previous, a);
        if (edgeOptional.isPresent()) {
            if (!route.getEdgesInRoute().contains(edgeOptional.get())) {
                route.addEdgeToRoute(edgeOptional.get());
            }
        }
        route.addVertexToRoute(a);
        if (a.equals(z)) {
            List<Route<V, E>> routes = new ArrayList<>();
            routes.add(route);
            return routes;
        }
        if (!vertices.contains(a)) {
            return Collections.emptyList();
        }
        List<Route<V, E>> routes = new ArrayList<>();
        for (Vertex<V> next : getNextVerticesConnectToVertex(a)) {
            if (!route.getVerticesInRoute().contains(next)) {
                List<Route<V, E>> newRoutes = getRoutes(a, next, z, route);
                routes.addAll(newRoutes);
            }
        }
        return routes;
    }

    private Set<Vertex<V>> getNextVerticesConnectToVertex(Vertex<V> a) {
        Set<Vertex<V>> result = new HashSet<>();
        result.addAll(getAllUniDirectionalEdgesAtVertex(a));
        result.addAll(getAllBiDirectionalEdgesAtVertex(a));
        return result;
    }

    private List<Vertex<V>> getAllBiDirectionalEdgesAtVertex(Vertex<V> a) {
        return edges.stream()
                .filter(edge -> (edge.isBi() && edge.endsAt(a)))
                .map(Edge::getAEnd)
                .collect(Collectors.toList());
    }

    private List<Vertex<V>> getAllUniDirectionalEdgesAtVertex(Vertex<V> a) {
        return edges.stream()
                .filter(edge -> edge.startsAt(a))
                .map(Edge::getZEnd)
                .collect(Collectors.toList());
    }

    private Optional<Edge<V, E>> resolveEdgeBetweenVertexes(Vertex<V> a, Vertex<V> z) {
        return edges.stream()
                .filter(edge -> edge.isUniDirectionalAndConnectsVertices(a, z)
                        || edge.isBiDirectionalAndConnectsVertices(a, z))
                .findFirst();
    }

}
