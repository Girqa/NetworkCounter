package MathCore.ShortCircuits;

import Factorys.DeviceFactory.DeviceFactory;
import MathCore.BaseElements.Device;
import MathCore.BaseElements.Node;
import MathCore.Interfaces.ShortCircuit;
import MathCore.Scheme.Scheme;

public class ThreePhaseShortCircuit implements ShortCircuit {
    private Node firstNode;
    private Node secondNode;
    private Node thirdNode;
    private Device shortCircuit12;
    private Device shortCircuit23;
    private double tolerance;

    public ThreePhaseShortCircuit(Node firstNode, Node secondNode, Node thirdNode, double tolerance) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.thirdNode = thirdNode;
        this.tolerance = tolerance;
    }

    @Override
    public void execute(Scheme scheme) {
        shortCircuit12 = DeviceFactory.createDevice(DeviceFactory.Devices.RESISTANCE,
                firstNode, secondNode, tolerance);
        shortCircuit23 = DeviceFactory.createDevice(DeviceFactory.Devices.RESISTANCE,
                secondNode, thirdNode, tolerance);
        scheme.getDevices().add(shortCircuit12);
        scheme.getDevices().add(shortCircuit23);
    }

    @Override
    public void rollBack(Scheme scheme) {
        scheme.getDevices().remove(shortCircuit12);
        scheme.getDevices().remove(shortCircuit23);
    }
}
