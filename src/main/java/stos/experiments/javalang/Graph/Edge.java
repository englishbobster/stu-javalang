package stos.experiments.javalang.Graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Edge {
    private final Vertex aEnd;
    private final Vertex zEnd;
    private final int value;

    boolean startsAt(Vertex a) {
        return a.equals(aEnd);
    }

    boolean endsAt(Vertex z) {
        return z.equals(zEnd);
    }

    @Override
    public String toString() {
        return aEnd.getLabel() + "-->[" + value + "]-->" + zEnd.getLabel();
    }
}
