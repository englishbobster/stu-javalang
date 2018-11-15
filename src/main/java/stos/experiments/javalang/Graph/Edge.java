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

    @Override
    public String toString() {
        if (directionality.equals(BIDIR)) {
            return aEnd.getLabel() + "<--[" + value + "]-->" + zEnd.getLabel();
        }
        return aEnd.getLabel() + "-->[" + value + "]-->" + zEnd.getLabel();
    }

}
