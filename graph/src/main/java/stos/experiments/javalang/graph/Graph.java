package stos.experiments.javalang.graph;

import java.util.List;

public interface Graph<V, E extends CostableEdge> {

    List<Route<V, E>> getRoutes(Vertex<V> a, Vertex<V> z);

    Route<V, E> findCheapestRoute(Vertex<V> a, Vertex<V> z);
}
