package Scheme;

import MathCore.BaseElements.Device;
import MathCore.BaseElements.Node;
import MathCore.BaseElements.Resistance;
import MathCore.BaseElements.VoltageSource;
import MathCore.Scheme.Scheme;
import org.apache.commons.math.complex.Complex;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SchemeTest {
    @Test
    void simpleTest() {
        Device R1 = new Resistance(10.0);
        Device R2 = new Resistance(10.0);
        Device E = new VoltageSource(100.0, 0.0);

        Node n1 = new Node();
        Node n2 = new Node();

        R1.setStartNode(n1);
        R1.setFinishNode(n2);
        R2.setStartNode(n1);
        R2.setFinishNode(n2);
        E.setStartNode(n2);
        E.setFinishNode(n1);

        List<Device> devices = new ArrayList<>(List.of(R1, R2, E));
        List<Node> nodes = new ArrayList<>(List.of(n1, n2));

        Scheme s = new Scheme(devices, nodes);

        s.fillMatrices();
        Complex[] U = s.solveSystem();

        assertEquals(-100, (int) U[0].getReal()-1);
    }
}
