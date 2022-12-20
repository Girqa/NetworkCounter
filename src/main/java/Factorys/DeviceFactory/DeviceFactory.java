package Factorys.DeviceFactory;

import Factorys.AbstractFactorys.*;
import MathCore.BaseElements.Device;
import MathCore.BaseElements.Node;

import java.io.UncheckedIOException;
import java.util.NoSuchElementException;
import java.util.zip.CheckedInputStream;

public class DeviceFactory {
    public enum Devices{
        RESISTANCE,
        CAPACITOR,
        INDUCTANCE,
        VOLTAGE_SOURCE,
        CURRENT_SOURCE;
    }

    private static final CapacitorFactory capacitorFactory = new CapacitorFactory();
    private static final InductanceFactory inductanceFactory = new InductanceFactory();
    private static final ResistanceFactory resistanceFactory = new ResistanceFactory();
    private static final CurrentSourceFactory currentSourceFactory = new CurrentSourceFactory();
    private static final VoltageSourceFactory voltageSourceFactory = new VoltageSourceFactory();


    public static Device createDevice(Devices type, Node startNode, Node finishNode, double... args) throws IllegalArgumentException, NoSuchElementException{
        Device device;
        switch(type) {
            case CAPACITOR:
                device = capacitorFactory.newInstance(args[0]);
                break;
            case INDUCTANCE:
                device = inductanceFactory.newInstance(args[0]);
                break;
            case RESISTANCE:
                device = resistanceFactory.newInstance(args[0]);
                break;
            case CURRENT_SOURCE:
                if (args.length < 3) {
                    throw new IllegalArgumentException("Uncorrected current source parameters. " +
                            "You must pass: voltage, phase, tolerance");
                }
                device = currentSourceFactory.newInstance(args[0], args[1], args[2]);
                break;
            case VOLTAGE_SOURCE:
                if (args.length < 3) {
                    throw new IllegalArgumentException("Uncorrected voltage source parameters. " +
                            "You must pass: current, phase, tolerance.");
                }
                device = voltageSourceFactory.newInstance(args[0], args[1], args[2]);
                break;
            default:
                throw new NoSuchElementException("Chosen illegal device type!");
        }
        device.setStartNode(startNode);
        device.setFinishNode(finishNode);
        return device;
    }
}
