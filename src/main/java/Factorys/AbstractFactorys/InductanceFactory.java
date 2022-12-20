package Factorys.AbstractFactorys;

import MathCore.BaseElements.Inductance;
import Interfaces.PassiveElementFactory;
import org.apache.commons.math.complex.Complex;

public class InductanceFactory implements PassiveElementFactory {
    public Inductance newInstance(double resistance) {
        Inductance inductance = new Inductance();
        inductance.setCurrent(Complex.ZERO);
        inductance.setVoltage(Complex.ZERO);
        inductance.setResistance(new Complex(0.0, resistance));
        return inductance;
    }
}
