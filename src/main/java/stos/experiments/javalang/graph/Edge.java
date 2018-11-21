package stos.experiments.javalang.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static stos.experiments.javalang.graph.Directionality.BIDIR;

@AllArgsConstructor
@Getter
class Edge<T extends hasEdgeCost> {
    private final Vertex aEnd;
    private final Vertex zEnd;
    private final Directionality directionality;
    private final T value;

    boolean startsAt(Vertex a) {
        return a.equals(aEnd);
    }

    boolean endsAt(Vertex z) {
        return z.equals(zEnd);
    }

    boolean isBi() {
        return directionality.equals(BIDIR);
    }

    boolean isUniDirectionalAndConnectsVertices(Vertex a, Vertex z) {
        return getAEnd().equals(a) && getZEnd().equals(z);
    }

    //if bidirectional we want to check a matches z and visa versa
    boolean isBiDirectionalAndConnectsVertices(Vertex a, Vertex z) {
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
