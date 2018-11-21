package stos.experiments.javalang.graph.testutils;

import lombok.Data;
import stos.experiments.javalang.graph.CostableEdge;

@Data
public
class EdgeCost implements CostableEdge {
    private final Integer val;

    @Override
    public int edgeCost() {
        return val;
    }
}
