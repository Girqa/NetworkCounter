package MathCore.BaseElements;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.math.complex.Complex;


@NoArgsConstructor
public class Node {
    private static int nodesCounter = 0;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private Complex potential = Complex.ZERO;
    public Node(String name) {
        this.name = name;
        this.id = nodesCounter++;
    }

    @Override
    public boolean equals(Object obj) {
        Node o = (Node) obj;
        return id == o.getId();
    }

    @Override
    public int hashCode() {
        return id;
    }
}
