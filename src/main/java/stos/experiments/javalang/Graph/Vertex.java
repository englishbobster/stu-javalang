package stos.experiments.javalang.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Vertex or Node?...go with Vertex to try and avoid name conflicts in other imports.
 * Besides, Vertex sounds more impressive.
 */
class Vertex {

    private List<Edge> edges;

    Vertex() {
        edges = new ArrayList<>();
    }

    void add(Edge edge) {
        edges.add(edge);
    }

    List<Edge> getEdges() {
        return edges;
    }
}
