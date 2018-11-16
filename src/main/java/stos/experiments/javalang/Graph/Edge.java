package stos.experiments.javalang.Graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static stos.experiments.javalang.Graph.Directionality.BIDIR;

@AllArgsConstructor
@Getter
class Edge {
    private final Vertex aEnd;
    private final Vertex zEnd;
    private final Directionality directionality;
    private final int value;

    boolean startsAt(Vertex a) {
        return a.equals(aEnd);
    }

    boolean endsAt(Vertex z) {
        return z.equals(zEnd);
    }

    boolean isBi() {
        return directionality.equals(BIDIR);
    }

    boolean IsUniDirectionalAndConnectsVertices(Vertex a, Vertex z) {
        return getAEnd().equals(a) && getZEnd().equals(z);
    }

    //if bidirectional we want to check a matches z and visa versa
    boolean isBiDirectionalAndConnectsVertices(Vertex a, Vertex z) {
        return isBi() && getAEnd().equals(z) && getZEnd().equals(a);
    }

    @Override
    public String toString() {
        if (directionality.equals(BIDIR)) {
            return aEnd.getLabel() + "<--[" + value + "]-->" + zEnd.getLabel();
        }
        return aEnd.getLabel() + "-->[" + value + "]-->" + zEnd.getLabel();
    }

}
