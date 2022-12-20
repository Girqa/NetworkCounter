package Factorys.AbstractFactorys;

import MathCore.BaseElements.CurrentSource;
import Interfaces.SourceFactory;
import org.apache.commons.math.complex.Complex;

public class CurrentSourceFactory implements SourceFactory {
    public CurrentSource newInstance(double current, double phase, double tolerance) {
        CurrentSource currentSource = new CurrentSource();
        currentSource.setCurrent(new Complex(
                current * Math.cos(phase),
                current * Math.sin(phase)
        ));
        currentSource.setVoltage(Complex.ZERO);
        currentSource.setResistance(new Complex(1 / tolerance, 0.0));
        return currentSource;
    }
}
