package MathCore.ShortCircuits;

import Factorys.DeviceFactory.DeviceFactory;
import MathCore.BaseElements.Device;
import MathCore.BaseElements.Node;
import MathCore.BaseElements.Resistance;
import MathCore.Interfaces.ShortCircuit;
import MathCore.Scheme.Scheme;

public class OnePhaseShortCircuit implements ShortCircuit {
    private Node shortedNode;
    private Node ground;
    private Device shortCircuit;
    private double tolerance;

    public OnePhaseShortCircuit(Node shortedNode, Node ground, double tolerance) {
        this.shortedNode = shortedNode;
        this.ground = ground;
        this.tolerance = tolerance;
    }

    @Override
    public void execute(Scheme scheme) {
        shortCircuit = DeviceFactory.createDevice(DeviceFactory.Devices.RESISTANCE,
                shortedNode, ground, tolerance);
        scheme.getDevices().add(shortCircuit);
    }

    @Override
    public void rollBack(Scheme scheme) {
        scheme.getDevices().remove(shortCircuit);
    }
}
