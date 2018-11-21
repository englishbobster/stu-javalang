package stos.experiments.javalang.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static stos.experiments.javalang.graph.Directionality.BIDIR;

@AllArgsConstructor
@Getter
class Edge<V, E extends CostableEdge> {
    private final Vertex<V> aEnd;
    private final Vertex<V> zEnd;
    private final Directionality directionality;
    private final E value;

    boolean startsAt(Vertex<V> a) {
        return a.equals(aEnd);
    }

    boolean endsAt(Vertex<V> z) {
        return z.equals(zEnd);
    }

    boolean isBi() {
        return directionality.equals(BIDIR);
    }

    boolean isUniDirectionalAndConnectsVertices(Vertex<V> a, Vertex<V> z) {
        return getAEnd().equals(a) && getZEnd().equals(z);
    }

    //if bidirectional we want to check a matches z and visa versa
    boolean isBiDirectionalAndConnectsVertices(Vertex<V> a, Vertex<V> z) {
        return isBi() && getAEnd().equals(z) && getZEnd().equals(a);
    }

    @Override
    public String toString() {
        if (directionality.equals(BIDIR)) {
            return aEnd.getLabel() + "<--[" + value.toString() + "]-->" + zEnd.getLabel();
        }
        return aEnd.getLabel() + "-->[" + value.toString() + "]-->" + zEnd.getLabel();
    }

}
