package MathCore.ShortCircuits;

import Factorys.DeviceFactory.DeviceFactory;
import MathCore.BaseElements.Device;
import MathCore.BaseElements.Node;
import MathCore.Interfaces.ShortCircuit;
import MathCore.Scheme.Scheme;

public class TwoPhaseShortCircuit implements ShortCircuit {
    private Node firstNode;
    private Node secondNode;
    private Device shortCircuit;

    private double tolerance;

    public TwoPhaseShortCircuit(Node firstNode, Node secondNode, double tolerance) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.tolerance = tolerance;
    }

    @Override
    public void execute(Scheme scheme) {
        shortCircuit = DeviceFactory.createDevice(DeviceFactory.Devices.RESISTANCE,
                firstNode, secondNode, tolerance);
        scheme.getDevices().add(shortCircuit);
    }

    @Override
    public void rollBack(Scheme scheme) {
        scheme.getDevices().remove(shortCircuit);
    }
}
