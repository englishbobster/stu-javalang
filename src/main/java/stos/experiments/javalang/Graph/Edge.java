package stos.experiments.javalang.Graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Edge {
    private final Vertex zEnd;
    private final int value;
}
