package Parsers;

import Factorys.DeviceFactory.DeviceFactory;
import MathCore.BaseElements.Device;
import MathCore.BaseElements.Node;
import MathCore.Interfaces.ShortCircuit;
import MathCore.Scheme.Scheme;
import MathCore.ShortCircuits.OnePhaseShortCircuit;
import MathCore.ShortCircuits.ThreePhaseShortCircuit;
import MathCore.ShortCircuits.TwoPhaseShortCircuit;
import org.apache.commons.math.complex.Complex;

import java.util.ArrayList;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        double tolerance = 10E-10;

        Node n0 = new Node("node 0");
        Node n1 = new Node("node 1");
        Node n2 = new Node("node 2");
        Node n3 = new Node("node 3");
        Node n4 = new Node("node 4");
        Node n5 = new Node("node 5");
        Node n6 = new Node("node 6");

        Device e1 = DeviceFactory.createDevice(DeviceFactory.Devices.VOLTAGE_SOURCE,
                n0, n1,
                220, 0, tolerance);

        Device e2 = DeviceFactory.createDevice(DeviceFactory.Devices.VOLTAGE_SOURCE,
                n0, n3,
                220, -120 * Math.PI / 180, tolerance);

        Device e3 = DeviceFactory.createDevice(DeviceFactory.Devices.VOLTAGE_SOURCE,
                n0, n5,
                220, 120 * Math.PI / 180, tolerance);

        Device l1 = DeviceFactory.createDevice(DeviceFactory.Devices.INDUCTANCE, n1, n2, 10);

        Device l2 = DeviceFactory.createDevice(DeviceFactory.Devices.INDUCTANCE, n3, n4, 10);

        Device l3 = DeviceFactory.createDevice(DeviceFactory.Devices.INDUCTANCE, n5, n6, 10);

        Device r1 = DeviceFactory.createDevice(DeviceFactory.Devices.RESISTANCE, n2, n0, 1/tolerance);

        Device r2 = DeviceFactory.createDevice(DeviceFactory.Devices.RESISTANCE, n4, n0, 10);

        Device r3 = DeviceFactory.createDevice(DeviceFactory.Devices.RESISTANCE, n6, n0, 10);

        List<Node> nodes = new ArrayList<>(List.of(n1, n2, n3, n4, n5, n6, n0));
        List<Device> devices = new ArrayList<>(List.of(e1, e2, e3, l1, l2, l3, r1, r2, r3));

        long startTime = System.nanoTime();
        Scheme scheme = new Scheme(devices, nodes);
        scheme.fillMatrices();
        scheme.solveSystem();

        System.out.println((System.nanoTime() - startTime)/1000 + " mks");
        System.out.println("Normal Mode");
        showResults(nodes, l1, l2, l3);


        // SC node2 on ground
        System.out.println("///////////////////");
        startTime = System.nanoTime();

        ShortCircuit sc1 = new OnePhaseShortCircuit(n2, n0, tolerance);
        sc1.execute(scheme);

        scheme.fillMatrices();
        scheme.solveSystem();

        System.out.println((System.nanoTime() - startTime)/1000 + " mks");
        System.out.println("One phase Short Circuit in node 2");
        showResults(nodes, l1,l2,l3);

        // Roll back SC
        System.out.println("///////////////////");
        startTime = System.nanoTime();

        sc1.rollBack(scheme);
        scheme.fillMatrices();
        scheme.solveSystem();

        System.out.println("Normal Mod");
        System.out.println((System.nanoTime() - startTime)/1000 + " mks");
        showResults(nodes, l1,l2,l3);

        // Three-phase sc
        System.out.println("///////////////////");
        startTime = System.nanoTime();

        ShortCircuit sc3 = new ThreePhaseShortCircuit(n2, n4, n6, tolerance);
        sc3.execute(scheme);

        scheme.fillMatrices();

        scheme.solveSystem();

        System.out.println("Three-phase sc");
        System.out.println((System.nanoTime() - startTime)/1000 + " mks");
        showResults(nodes, l1,l2,l3);
        System.out.println((System.nanoTime() - startTime)/1000 + " mks");

        // Roll back Three-phase sc
        System.out.println("///////////////////");
        startTime = System.nanoTime();

        sc3.rollBack(scheme);
        scheme.fillMatrices();
        scheme.solveSystem();

        System.out.println("Three-phase sc rolled back");
        System.out.println((System.nanoTime() - startTime)/1000 + " mks");
        showResults(nodes, l1,l2,l3);

        // Two-phase sc
        System.out.println("///////////////////");
        startTime = System.nanoTime();

        ShortCircuit sc2 = new TwoPhaseShortCircuit(n2, n4, tolerance);
        sc2.execute(scheme);

        scheme.fillMatrices();

        scheme.solveSystem();

        System.out.println("Two-phase sc");
        System.out.println((System.nanoTime() - startTime)/1000 + " mks");
        showResults(nodes, l1,l2,l3);

    }

    public static double round(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public static void showResults(List<Node> nodes, Device l1, Device l2, Device l3) {
            for (Node node: nodes) {
                System.out.println(
                        node.getName() + " u=" +
                                round(node.getPotential().abs(), 3)+ "∠" +
                                Math.round(
                                        Math.toDegrees(node.getPotential().getArgument())));
            }
            System.out.println("I1: " +
                    round(l1.getElementCurrent().abs(), 3)+"∠"+
                            Math.round(Math.toDegrees(l1.getElementCurrent().getArgument())));
            System.out.println("I2: " +
                    round(l2.getElementCurrent().abs(), 3)+"∠"+
                            Math.round(Math.toDegrees(l2.getElementCurrent().getArgument())));
            System.out.println("I3: " +
                    round(l3.getElementCurrent().abs(), 3)+"∠"+
                            Math.round(Math.toDegrees(l3.getElementCurrent().getArgument())));
    }

    public static String showComplex(Complex val) {
        return round(val.abs(), 3) + "∠" + round(Math.toDegrees(val.getArgument()), 3);
    }
}
