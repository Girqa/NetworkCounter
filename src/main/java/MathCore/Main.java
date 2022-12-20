package MathCore;

import MathCore.BaseElements.*;
import MathCore.Interfaces.CurrentCountable;
import MathCore.Scheme.Scheme;
import org.apache.commons.math.complex.Complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Device R = new Resistance(10.0);
        Device XL = new Inductance(10.0);
        Device XC = new Capacitor(10.0);
        Device E = new VoltageSource(100.0, 0.0);

        Node n0 = new Node();
        Node n1 = new Node();
        Node n2 = new Node();

        R.setStartNode(n0);
        R.setFinishNode(n1);
        XL.setStartNode(n1);
        XL.setFinishNode(n2);
        XC.setStartNode(n1);
        XC.setFinishNode(n2);
        E.setStartNode(n2);
        E.setFinishNode(n0);

        List<Device> devices = new ArrayList<>(List.of(R, XL, XC, E));
        List<Node> nodes = new ArrayList<>(List.of(n0, n1, n2));

        Scheme s = new Scheme(devices, nodes);

        s.fillMatrices();
        s.solveSystem();

    }
}
