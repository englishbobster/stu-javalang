package stos.experiments.javalang.graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Vertex or Node?...go with Vertex to try and avoid name conflicts in other imports.
 * Besides, Vertex sounds more impressive.
 */
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
class Vertex<V> {
    private final String label;
    private final V value;

    @Override
    public String toString() {
        return label + " : " + value.toString();
    }
}
