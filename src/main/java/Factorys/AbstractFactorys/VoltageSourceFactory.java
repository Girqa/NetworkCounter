package Factorys.AbstractFactorys;

import MathCore.BaseElements.VoltageSource;
import Interfaces.SourceFactory;
import org.apache.commons.math.complex.Complex;

public class VoltageSourceFactory implements SourceFactory {
    public VoltageSource newInstance(double voltage, double phase, double tolerance) {
        VoltageSource source = new VoltageSource();
        source.setVoltage(new Complex(
                voltage*Math.cos(phase),
                voltage*Math.sin(phase)
        ));
        source.setResistance(new Complex(tolerance, 0.0));
        source.setCurrent(Complex.ZERO);
        return source;
    }
}
