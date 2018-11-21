package stos.experiments.javalang.graph.testutils;

import lombok.Data;
import stos.experiments.javalang.graph.hasEdgeCost;

@Data
public
class EdgeCost implements hasEdgeCost {
    private final Integer val;

    @Override
    public int costOfEdge() {
        return val;
    }
}
