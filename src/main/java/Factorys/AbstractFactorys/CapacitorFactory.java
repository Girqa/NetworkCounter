package Factorys.AbstractFactorys;

import MathCore.BaseElements.Capacitor;
import Interfaces.PassiveElementFactory;
import org.apache.commons.math.complex.Complex;

public class CapacitorFactory implements PassiveElementFactory {
    public Capacitor newInstance(double resistance) {
        Capacitor capacitor = new Capacitor();
        capacitor.setResistance(new Complex(0.0, -resistance));
        capacitor.setVoltage(Complex.ZERO);
        capacitor.setCurrent(Complex.ZERO);
        return capacitor;
    }
}
